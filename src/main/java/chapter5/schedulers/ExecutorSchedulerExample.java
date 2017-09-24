package chapter5.schedulers;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ExecutorSchedulerExample {

    public void basic() {
        final int THREAD_NUM = 10;

        String[] data = {"1","3","5"};
        Observable<String> source = Observable.fromArray(data);
        Executor executor = Executors.newFixedThreadPool(THREAD_NUM);

        source.subscribeOn(Schedulers.from(executor))
                .subscribe(Log::it);
        source.subscribeOn(Schedulers.from(executor))
                .subscribe(Log::it);
        CommonUtils.sleep(500);
    }

    public static void main(String[] args) {
        ExecutorSchedulerExample demo = new ExecutorSchedulerExample();
        demo.basic();
    }

}
