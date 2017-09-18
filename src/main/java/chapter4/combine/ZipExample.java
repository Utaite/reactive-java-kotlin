package chapter4.combine;

import common.CommonUtils;
import common.Log;
import common.Shape;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class ZipExample {

    public void marbleDiagram() {
        String[] shapes = {"BALL", "PENTAGON", "STAR"};
        String[] coloredTriangles = {"2-T", "6-T", "4-T"};

        Observable<String> source = Observable.zip(
                Observable.fromArray(shapes).map(Shape::getSuffix),
                Observable.fromArray(coloredTriangles).map(Shape::getColor),
                (suffix, color) -> color + suffix);

        source.subscribe(Log::it);
    }

    public void zipNumbers() {
        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                Observable.just(1, 2, 3),
                (a, b, c) -> a + b + c);

        source.subscribe(Log::it);
    }

    public void zipInterval() {
        Observable<String> source = Observable.zip(
                Observable.just("RED", "GREEN", "BLUE"),
                Observable.interval(200L, TimeUnit.MILLISECONDS),
                (value, i) -> value);

        CommonUtils.exampleStart();
        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public void zipWithNumbers() {
        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                (a, b) -> a + b)
                .zipWith(Observable.just(1, 2, 3), (ab, c) -> ab + c);
        source.subscribe(Log::it);
    }

    public static void main(String[] args) {
        ZipExample demo = new ZipExample();
        demo.marbleDiagram();
        demo.zipNumbers();
        demo.zipInterval();
        demo.zipWithNumbers();
    }

}
