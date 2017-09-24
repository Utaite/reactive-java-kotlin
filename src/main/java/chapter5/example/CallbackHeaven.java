package chapter5.example;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class CallbackHeaven {

    private static final String FIRST_URL = "https://api.github.com/zen";
    private static final String SECOND_URL = CommonUtils.GITHUB_ROOT + "/samples/callback_heaven";

    public void usingConcat() {
        CommonUtils.exampleStart();
        Observable<String> source = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get)
                .concatWith(Observable.just(SECOND_URL)
                        .map(OkHttpHelper::get));
        source.subscribe(Log::it);
        CommonUtils.sleep(5000);
    }

    public void usingZip() {
        CommonUtils.exampleStart();
        Observable<String> first = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);
        Observable<String> second = Observable.just(SECOND_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);

        Observable.zip(first, second, (a, b) -> ("\n>> " + a + "\n>> " + b))
                .subscribe(Log::it);

        CommonUtils.sleep(5000);
    }

    public static void main(String[] args) {
        CallbackHeaven demo = new CallbackHeaven();
        // demo.usingConcat();
        demo.usingZip();
    }

}
