package chapter4.conditional;

import common.Log;
import common.Shape;
import io.reactivex.Observable;
import io.reactivex.Single;


public class AllFunctionExample {

    public void marbleDiagram() {
        String[] data = {"1", "2", "3", "4"};

        Single<Boolean> source = Observable.fromArray(data)
                .map(Shape::getShape)
                .all(Shape.BALL::equals);
        source.subscribe(Log::it);
    }

    public static void main(String[] args) {
        AllFunctionExample demo = new AllFunctionExample();
        demo.marbleDiagram();
    }

}
