package chapter3;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class FlatMapExample {

    public void marbleDiagram() {
        Function<String, Observable<String>> getDoubleDiamonds =
                ball -> Observable.just(ball + "<>", ball + "<>");

        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(balls)
                .flatMap(getDoubleDiamonds);
        source.subscribe(System.out::println);
    }

    public void flatMapLambda() {
        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.fromArray(balls)
                .flatMap(ball -> Observable.just(ball + "<>", ball + "<>"));
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        FlatMapExample demo = new FlatMapExample();
        demo.marbleDiagram();
        demo.flatMapLambda();
    }

}
