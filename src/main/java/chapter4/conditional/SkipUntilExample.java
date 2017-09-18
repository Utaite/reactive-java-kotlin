package chapter4.conditional;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class SkipUntilExample {

    public void marbleDiagram() {
        String[] data = {"1", "2", "3", "4", "5", "6"};

        Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (val, notUsed) -> val)
                .skipUntil(Observable.timer(500L, TimeUnit.MILLISECONDS));

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        SkipUntilExample demo = new SkipUntilExample();
        demo.marbleDiagram();
    }

}
