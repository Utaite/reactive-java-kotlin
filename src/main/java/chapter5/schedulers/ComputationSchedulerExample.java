package chapter5.schedulers;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class ComputationSchedulerExample {

    public void basic() {
        CommonUtils.exampleStart();

        String[] objs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(objs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a);

        source.map(item -> "<<" + item + ">>")
                .subscribe(Log::it);

        source.map(item -> "##" + item + "##")
                .subscribe(Log::it);

        CommonUtils.sleep(500);
    }

    public static void main(String[] args) {
        ComputationSchedulerExample demo = new ComputationSchedulerExample();
        demo.basic();
    }

}
