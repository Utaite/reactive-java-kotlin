package chapter4.conditional;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class AmbExample {

    public void marbleDiagram() {
        String[] data1 = {"1", "3", "5"};
        String[] data2 = {"2-R", "4-R"};

        List<Observable<String>> sources = Arrays.asList(
                Observable.fromArray(data1)
                        .doOnComplete(() -> Log.it("Observable #1: onComplete()")),
                Observable.fromArray(data2)
                        .delay(100L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> Log.it("Observable #2: onComplete()"))
                );

        Observable.amb(sources)
                .doOnComplete(() -> Log.it("Result : onComplete()"))
                .subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        AmbExample demo = new AmbExample();
        demo.marbleDiagram();
    }

}
