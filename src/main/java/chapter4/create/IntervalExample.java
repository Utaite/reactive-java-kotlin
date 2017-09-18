package chapter4.create;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class IntervalExample {

    public void printNumbers() {
        CommonUtils.exampleStart();
        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(data -> (data + 1) * 100)
                .take(5);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public void noInitialDelay() {
        CommonUtils.exampleStart();
        Observable<Long> source = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .map(val -> val + 100)
                .take(5);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        IntervalExample demo = new IntervalExample();
        demo.printNumbers();
        demo.noInitialDelay();
    }

}
