package chapter7.flowcontrol;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class BufferExample {

    public void marbleDiagram() {
        String[] data = {"1", "2", "3", "4", "5", "6"};
        CommonUtils.exampleStart();

        // 앞의 3개는 100ms 간격으로 발행
        Observable<String> earlySource = Observable.fromArray(data)
                .take(3)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        Observable<String> middleSource = Observable.just(data[3])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), (a, b) -> a);

        Observable<String> lateSource = Observable.just(data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        Observable<List<String>> source = Observable.concat(earlySource, middleSource, lateSource)
                .buffer(3);

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public void bufferSkip() {
        String[] data = {"1", "2", "3", "4", "5", "6"};
        CommonUtils.exampleStart();

        // 앞의 3개는 100ms 간격으로 발행
        Observable<String> earlySource = Observable.fromArray(data)
                .take(3)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        Observable<String> middleSource = Observable.just(data[3])
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), (a, b) -> a);

        Observable<String> lateSource = Observable.just(data[4], data[5])
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        Observable<List<String>> source = Observable.concat(earlySource, middleSource, lateSource)
                .buffer(2, 3);

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        BufferExample demo = new BufferExample();
        // demo.marbleDiagram();
        demo.bufferSkip();
    }

}
