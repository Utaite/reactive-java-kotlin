package chapter4.etc;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class DelayExample {

    public void marbleDiagram() {
        CommonUtils.exampleStart();
        String[] data = {"1", "7", "2", "3", "4"};
        Observable<String> source = Observable.fromArray(data)
                .delay(100L, TimeUnit.MILLISECONDS);
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        DelayExample demo = new DelayExample();
        demo.marbleDiagram();
    }

}
