package chapter5.schedulers;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class SingleSchedulerExample {

    public void basic() {
        Observable<Integer> numbers = Observable.range(100, 5);
        Observable<String> chars = Observable.range(0, 5)
                .map(CommonUtils::numberToAlphabet);

        numbers.subscribeOn(Schedulers.single())
                .subscribe(Log::it);
        chars.subscribeOn(Schedulers.single())
                .subscribe(Log::it);

        CommonUtils.sleep(500);
    }

    public static void main(String[] args) {
        SingleSchedulerExample demo = new SingleSchedulerExample();
        demo.basic();
    }

}
