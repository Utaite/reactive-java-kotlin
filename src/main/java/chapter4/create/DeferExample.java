package chapter4.create;

import common.Log;
import common.Shape;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;


public class DeferExample {

    Iterator<String> colors = Arrays.asList("1", "3", "5", "6").iterator();

    public void marbleDiagram() {
        Callable<Observable<String>> supplier = () -> getObservable();
        Observable<String> source = Observable.defer(supplier);

        source.subscribe(val -> Log.it("Subscriber #1 -> " + val));
        source.subscribe(val -> Log.it("Subscriber #2 -> " + val));
    }

    public void notDeferred() {
        Observable<String> source = getObservable();
        source.subscribe(val -> Log.it("Subscriber #1 -> " + val));
        source.subscribe(val -> Log.it("Subscriber #2 -> " + val));
    }

    private Observable<String> getObservable() {
        if (colors.hasNext()) {
            String color = colors.next();
            return Observable.just(
                    Shape.getString(color, Shape.BALL),
                    Shape.getString(color, Shape.RECTANGLE),
                    Shape.getString(color, Shape.PENTAGON));
        }
        return Observable.empty();
    }

    public static void main(String[] args) {
        DeferExample demo = new DeferExample();
        demo.marbleDiagram();
        demo.notDeferred();
    }

}
