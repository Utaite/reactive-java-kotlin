package chapter7.flowcontrol;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class WindowExample {

    public void marbleDiagram() {
        String[] data = {"1", "2", "3", "4", "5", "6"};
        CommonUtils.exampleStart();

        // 앞의 3개는 100ms 간격으로 발행
        Observable<String> earlySource = Observable.fromArray(data)
                .take(3)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 가운데 1개는 300ms 후에 발행
        Observable<String> middleSource = Observable.just(data[3])
                .zipWith(Observable.timer(300L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 마지막 2개는 100ms 후에 발행
        Observable<String> lateSource = Observable.just(data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        // 데이터 3개씩 모아서 새로운 Observable을 생성함
        Observable<Observable<String>> source = Observable.concat(earlySource, middleSource, lateSource)
                .window(3);

        source.subscribe(observable -> {
            Log.it("New Observable Started!!");
            observable.subscribe(Log::it);
        });

        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        WindowExample demo = new WindowExample();
        demo.marbleDiagram();
    }

}
