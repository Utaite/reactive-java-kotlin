package chapter5.schedulers;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class TrampolineSchedulerExample {

    public void basic() {
        String[] objs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(objs);

        source.subscribeOn(Schedulers.trampoline())
                .map(data -> "<<" + data + ">>")
                .subscribe(Log::it);

        source.subscribeOn(Schedulers.trampoline())
                .map(data -> "##" + data + "##")
                .subscribe(Log::it);
        CommonUtils.sleep(500);
    }

    public static void main(String[] args) {
        TrampolineSchedulerExample demo = new TrampolineSchedulerExample();
        demo.basic();
    }

}
