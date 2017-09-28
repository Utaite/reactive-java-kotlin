package chapter7.flowcontrol;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class ThrottleFirstExample {

    public void marbleDiagram() {
        String[] data = {"1", "2", "3", "4", "5", "6"};
        CommonUtils.exampleStart();

        // 앞의 1개는 100ms 간격으로 발행
        Observable<String> earlySource = Observable.just(data[0])
                .zipWith(Observable.timer(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 다음 1개는 300ms 후에 발행
        Observable<String> middleSource = Observable.just(data[1])
                .zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 마지막 4개는 100ms 후에 발행
        Observable<String> lateSource = Observable.just(data[2], data[3], data[4], data[5])
                .zipWith(Observable.interval(105L, TimeUnit.MILLISECONDS), (a, b) -> a)
                .doOnNext(onNext -> Log.it("doOnNext / " + onNext));

        // 200ms 간격으로 throttleFirst() 실행함
        Observable<String> source = Observable.concat(earlySource, middleSource, lateSource)
                .throttleFirst(200L, TimeUnit.MILLISECONDS);

        source.subscribe(Log::it);
        CommonUtils.sleep(2000);
    }

    public static void main(String[] args) {
        ThrottleFirstExample demo = new ThrottleFirstExample();
        demo.marbleDiagram();
    }

}
