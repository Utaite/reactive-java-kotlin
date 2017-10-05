package chapter8;

import common.CommonUtils;
import common.Log;
import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class BackPressureExample {

    public void makeBackpressure() {
        CommonUtils.exampleStart();

        PublishSubject<Integer> subject = PublishSubject.create();
        subject.observeOn(Schedulers.computation())
                .subscribe(data -> {
                    CommonUtils.sleep(100);
                    Log.it(data);
                }, err -> Log.it(err.toString()));

        // 뜨거운 Observable 로 50,000,000개의 데이터를 연속으로 발행함
        for (int i = 0; i < 50000000; i++) {
            subject.onNext(i);
        }
        subject.onComplete();
    }

    public void usingBuffer() {
        CommonUtils.exampleStart();

        Flowable.range(1, 50000000)
                .onBackpressureBuffer(128, () -> {
                }, BackpressureOverflowStrategy.DROP_LATEST)
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    CommonUtils.sleep(100);
                    Log.it(data);
                }, err -> Log.it(err.toString()));
    }

    public void usingDrop() {
        CommonUtils.exampleStart();

        Flowable.range(1, 50000000)
                .onBackpressureDrop()
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    CommonUtils.sleep(100);
                    Log.it(data);
                }, err -> Log.it(err.toString()));

        CommonUtils.sleep(20000);
    }

    public void usingLatest() {
        CommonUtils.exampleStart();

        Flowable.range(1, 50000000)
                .onBackpressureLatest()
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    CommonUtils.sleep(100);
                    Log.it(data);
                }, err -> Log.it(err.toString()));

        CommonUtils.sleep(20000);
    }

    public static void main(String[] args) {
        BackPressureExample demo = new BackPressureExample();
        // demo.makeBackpressure();
        // demo.usingBuffer();
        // demo.usingDrop();
        demo.usingLatest();
    }

}
