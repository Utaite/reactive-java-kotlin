package chapter4.etc;

import common.Log;
import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;


public class MathFunctionsExample {

    public void marbleDiagram() {
        Integer[] data = {1, 2, 3, 4};

        // 1. Count
        Single<Long> source = Observable.fromArray(data)
                .count();
        source.subscribe(count -> Log.it("count is " + count));

        // 2. max() & min()
        Flowable.fromArray(data)
                .to(MathFlowable::max)
                .subscribe(max -> Log.it("max is " + max));

        Flowable.fromArray(data)
                .to(MathFlowable::min)
                .subscribe(min -> Log.it("min is " + min));

        // 3. sum() & average
        Flowable<Integer> flowable = Flowable.fromArray(data)
                .to(MathFlowable::sumInt);
        flowable.subscribe(sum -> Log.it("sum is " + sum));

        Flowable<Double> flowable2 = Observable.fromArray(data)
                .toFlowable(BackpressureStrategy.BUFFER)
                .to(MathFlowable::averageDouble);
        flowable2.subscribe(avg -> Log.it("average is " + avg));
    }

    public static void main(String[] args) {
        MathFunctionsExample demo = new MathFunctionsExample();
        demo.marbleDiagram();
    }

}
