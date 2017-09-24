package chapter5;

import common.CommonUtils;
import common.Log;
import common.Shape;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


public class FlipExample {

    public void marbleDiagram() {
        String[] objs = {"1-S", "2-T", "3-P"};
        Observable<String> source = Observable.fromArray(objs)
                .doOnNext(data -> Log.it("Original data = " + data))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .map(Shape::flip);
        source.subscribe(Log::it);
        CommonUtils.sleep(500);
    }

    public void observeOnRemoved() {
        String[] objs = {"1-S", "2-T", "3-P"};
        Observable<String> source = Observable.fromArray(objs)
                .doOnNext(data -> Log.it("Original data = " + data))
                .subscribeOn(Schedulers.newThread())
                // .observeOn(Schedulers.newThread())
                .map(Shape::flip);
        source.subscribe(Log::it);
        CommonUtils.sleep(500);
    }

    public static void main(String[] args) {
        FlipExample demo = new FlipExample();
        demo.marbleDiagram();
        demo.observeOnRemoved();
    }

}
