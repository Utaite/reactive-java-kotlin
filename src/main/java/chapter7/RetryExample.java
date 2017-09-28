package chapter7;

import common.CommonUtils;
import common.Log;
import common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;


public class RetryExample {

    public void retry5() {
        CommonUtils.exampleStart();

        String url = "https://api.github.com/zen";
        Observable<String> source = Observable.just(url)
                .map(OkHttpHelper::get)
                .retry(5)
                .onErrorReturn(e -> CommonUtils.ERROR_CODE);

        source.subscribe(data -> Log.it("result : " + data));
    }

    public void retryWithDelay() {
        final int RETRY_MAX = 5;
        final int RETRY_DELAY = 1000;

        CommonUtils.exampleStart();

        String url = " https://api.github.com/zen";
        Observable<String> source = Observable.just(url)
                .map(OkHttpHelper::get)
                .retry((retryCnt, e) -> {
                    Log.it("retryCnt = " + retryCnt);
                    CommonUtils.sleep(RETRY_DELAY);

                    return retryCnt < RETRY_MAX;
                })
                .onErrorReturn(e -> CommonUtils.ERROR_CODE);

        source.subscribe(data -> Log.it("result : " + data));
    }

    public void retryUntil() {
        CommonUtils.exampleStart();

        String url = "https://api.github.com/zen";
        Observable<String> source = Observable.just(url)
                .map(OkHttpHelper::get)
                .subscribeOn(Schedulers.io())
                .retryUntil(() -> {
                    if(CommonUtils.isNetworkAvailable()) {
                        return true;
                    }
                    CommonUtils.sleep(1000);
                    return false;
                });
        source.subscribe(Log::it);

        CommonUtils.sleep(5000);
    }

    public void retryWhen() {
        Observable.create((ObservableEmitter<String> emitter) -> {
            emitter.onError(new RuntimeException("always fails"));
        }).retryWhen(attempts -> {
            return attempts.zipWith(Observable.range(1, 3), (n, i) -> i)
                    .flatMap(i -> {
                        Log.it("delay retry by " + i + " seconds");
                        return Observable.timer(1 , TimeUnit.SECONDS);
                    });
        }).blockingForEach(Log::it);
    }

    public static void main(String[] args) {
        RetryExample demo = new RetryExample();
        // demo.retry5();
        // demo.retryWithDelay();
        // demo.retryUntil();
        demo.retryWhen();
    }

}
