package chapter4.transporm;

import common.Shape;
import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;


public class GroupByExample {

    public void marbleDiagram() {
        String[] objs = {"6", "4", "2-T", "2", "6-T", "4-T"};
        Observable<GroupedObservable<String, String>> source =
                Observable.fromArray(objs).groupBy(Shape::getShape);

        source.subscribe(obj ->
                obj.subscribe(val -> System.out.println("Group: " + obj.getKey() + "\t Value: " + val)));
    }

    public void filterBallGroup() {
        String[] objs = {"6", "4", "2-T", "2", "6-T", "4-T"};
        Observable<GroupedObservable<String, String>> source =
                Observable.fromArray(objs).groupBy(Shape::getShape);

        source.subscribe(obj ->
                obj.filter(val -> obj.getKey().equals(Shape.BALL))
                        .subscribe(val -> System.out.println("Group: " + obj.getKey() + "\t Value: " + val)));
    }

    public static void main(String[] args) {
        GroupByExample demo = new GroupByExample();
        demo.marbleDiagram();
        demo.filterBallGroup();
    }

}
