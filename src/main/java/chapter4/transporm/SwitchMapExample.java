package chapter4.transporm;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class SwitchMapExample {

    public void marbleDiagram() {
        CommonUtils.exampleStart();
        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2)
                );
        source.subscribe(Log::it);
        CommonUtils.sleep(2000);
    }

    public void usingDoOnNext() {
        CommonUtils.exampleStart();
        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .doOnNext(Log::it)
                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2)
                );
        source.subscribe(Log::it);
        CommonUtils.sleep(2000);
    }

    public static void main(String[] args) {
        SwitchMapExample demo = new SwitchMapExample();
        demo.marbleDiagram();
        demo.usingDoOnNext();
    }

}
