package chapter4.create;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class IntervalRangeExample {

    public void printNumbers() {
        Observable<Long> source = Observable.intervalRange(1, 5, 100L, 100L, TimeUnit.MILLISECONDS);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public void makeWithInterval() {
        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(val -> val + 1)
                .take(5);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        IntervalRangeExample demo = new IntervalRangeExample();
        demo.printNumbers();
        demo.makeWithInterval();
    }

}
