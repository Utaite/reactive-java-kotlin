package chapter4.create;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class RepeatExample {

    public void marbleDiagram() {
        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(balls)
                .repeat(3);

        source.doOnComplete(() -> Log.it("onComplete"))
                .subscribe(Log::it);
    }

    public void heartbeatV1() {
        String serverUrl = "https://api.github.com/zen";

        Observable.timer(2, TimeUnit.SECONDS)
                .map(val -> serverUrl)
                .map(OkHttpHelper::get)
                .repeat()
                .subscribe(res -> Log.it("Ping Result : " + res));
        CommonUtils.sleep(10000);
    }

    public static void main(String[] args) {
        RepeatExample demo = new RepeatExample();
        demo.marbleDiagram();
        demo.heartbeatV1();
    }

}
