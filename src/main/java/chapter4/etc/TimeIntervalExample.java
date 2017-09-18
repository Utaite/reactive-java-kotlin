package chapter4.etc;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;


public class TimeIntervalExample {

    public void marbleDiagram() {
        String[] data = {"1","3","7"};

        CommonUtils.exampleStart();
        Observable<Timed<String>> source = Observable.fromArray(data)
                .delay(item -> {
                    CommonUtils.doSomething();
                    return Observable.just(item);
                })
                .timeInterval();

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        TimeIntervalExample demo = new TimeIntervalExample();
        demo.marbleDiagram();
    }

}
