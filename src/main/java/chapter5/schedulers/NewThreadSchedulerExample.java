package chapter5.schedulers;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class NewThreadSchedulerExample {

    public void basic() {
        String[] objs = {"1", "3", "5"};
        Observable.fromArray(objs)
                .doOnNext(data -> Log.it("Original data : " + data))
                .map(data -> "<<" + data + ">>")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::it);
        CommonUtils.sleep(500);

        Observable.fromArray(objs)
                .doOnNext(data -> Log.it("Original data : " + data))
                .map(data -> "##" + data + "##")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::it);
        CommonUtils.sleep(500);
    }

    public static void main(String[] args) {
        NewThreadSchedulerExample demo = new NewThreadSchedulerExample();
        demo.basic();
    }

}
