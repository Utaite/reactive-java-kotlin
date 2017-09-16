package chapter3;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class MapExample {

    public void marbleDiagram() {
        String[] balls = {"1", "2", "3", "5"};
        Observable<String> source = Observable.fromArray(balls)
                .map(ball -> ball + "<>");
        source.subscribe(System.out::println);
    }

    public void mapFunction() {
        Function<String, String> getDiamond = ball -> ball + "<>";

        String[] balls = {"1", "2", "3", "5"};
        Observable<String> source = Observable.fromArray(balls)
                .map(getDiamond);
        source.subscribe(System.out::println);
    }

    public void mappingType() {
        Function<String, Integer> ballToIndex = ball -> {
            switch (ball) {
                case "RED":
                    return 1;
                case "YELLOW":
                    return 2;
                case "GREEN":
                    return 3;
                case "BLUE":
                    return 5;
                default:
                    return -1;
            }
        };

        String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"};
        Observable<Integer> source = Observable.fromArray(balls)
                .map(ballToIndex);
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        MapExample demo = new MapExample();
        demo.marbleDiagram();
        demo.mapFunction();
        demo.mappingType();
    }

}
