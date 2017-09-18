package chapter4.combine;

import common.CommonUtils;
import common.Log;
import common.Shape;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class CombineLatestExample {

    public void marbleDiagram() {
        String[] data1 = {"6", "7", "4", "2"};
        String[] data2 = {"DIAMOND", "STAR", "PENTAGON"};

        Observable<String> source = Observable.combineLatest(
                Observable.fromArray(data1)
                        .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> Shape.getColor(shape)),
                Observable.fromArray(data2)
                        .zipWith(Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> Shape.getSuffix(shape)),
                (v1, v2) -> v1 + v2);

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        CombineLatestExample demo = new CombineLatestExample();
        demo.marbleDiagram();
    }

}
