package chapter5.schedulers;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.File;


public class IOSchedulerExample {

    public void basic() {
        String root = "/Users/n2soft/Desktop";
        File[] files = new File(root).listFiles();
        Observable<String> source = Observable.fromArray(files)
                .filter(f -> !f.isDirectory())
                .map(f -> f.getAbsolutePath())
                .subscribeOn(Schedulers.io());

        source.subscribe(Log::it);
        CommonUtils.sleep(500);
    }

    public static void main(String[] args) {
        IOSchedulerExample demo = new IOSchedulerExample();
        demo.basic();
    }

}
