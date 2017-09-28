package chapter7;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;


public class DoOnExample {

    public void basic() {
        String[] objs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(objs);

        source.doOnNext(data -> Log.it("onNext / " + data))
                .doOnComplete(() -> Log.it("onComplete"))
                .doOnError(e -> Log.it("onError / " + e.getMessage()))
                .subscribe(Log::it);
    }

    public void withError() {
        Integer[] divider = {10, 5, 0};

        Observable.fromArray(divider)
                .map(div -> 1000 / div)
                .doOnNext(data -> Log.it("onNext / " + data))
                .doOnComplete(() -> Log.it("onComplete"))
                .doOnError(e -> Log.it("onError / " + e.getMessage()))
                .subscribe(Log::it);
    }

    public void doOnEach() {
        String[] data = {"ONE", "TWO", "THREE"};
        Observable<String> source = Observable.fromArray(data);

        source.doOnEach(noti -> {
            if (noti.isOnNext()) {
                Log.it("onNext / " + noti.getValue());
            } else if (noti.isOnComplete()) {
                Log.it("onComplete");
            } else if (noti.isOnError()) {
                Log.it("onError / " + noti.getError().getMessage());
            }
        }).subscribe(Log::it);
    }

    public void doOnEachObserver() {
        String[] objs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(objs);

        source.doOnEach(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                // doOnEach()에서는 onSubscribe() 함수가 호출되지 않습니다.
            }

            @Override
            public void onNext(String s) {
                Log.it("onNext / " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.it("onError / " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.it("onComplete");
            }
        }).subscribe(Log::it);
    }

    public void doOnSubscribeAndDispose() {
        String[] objs = {"1", "3", "5", "2", "6"};
        Observable<String> source = Observable.fromArray(objs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a)
                .doOnSubscribe(notUsed -> Log.it("onSubscribe"))
                .doOnDispose(() -> Log.it("onDispose"));
        Disposable d = source.subscribe(Log::it);

        CommonUtils.sleep(200);
        d.dispose();
        CommonUtils.sleep(300);
    }

    public void doOnLifeCycle() {
        String[] objs = {"1", "3", "5", "2", "6"};
        Observable<String> source = Observable.fromArray(objs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a, b) -> a)
                .doOnLifecycle(
                        d -> Log.it("onSubscribe"), () -> Log.it("onDispose"));
        Disposable d = source.subscribe(Log::it);

        CommonUtils.sleep(200);
        d.dispose();
        CommonUtils.sleep(300);
    }

    public void doOnTerminate() {
        String[] objs = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(objs);

        source.doOnTerminate(() -> Log.it("onTerminate"))
                .doOnComplete(() -> Log.it("onComplete"))
                .doOnError(e -> Log.it("onError / " + e.getMessage()))
                .subscribe(Log::it);
    }

    public static void main(String[] args) {
        DoOnExample demo = new DoOnExample();
        // demo.basic();
        // demo.withError();
        // demo.doOnEach();
        // demo.doOnEachObserver();
        // demo.doOnSubscribeAndDispose();
        // demo.doOnLifeCycle();
        demo.doOnTerminate();
    }

}
