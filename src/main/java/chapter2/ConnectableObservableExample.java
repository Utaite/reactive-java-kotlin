package chapter2;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import java.util.concurrent.TimeUnit;


public class ConnectableObservableExample {

    public void marbleDiagram() {
        String[] dt = {"1", "3", "5"};
        Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(i -> dt[i])
                .take(dt.length);
        ConnectableObservable<String> source = balls.publish();
        source.subscribe(data -> System.out.println("Subscriber #1 -> " + data));
        source.subscribe(data -> System.out.println("Subscriber #2 -> " + data));
        source.connect();

        sleep(250);
        source.subscribe(data -> System.out.println("Subscriber #3 -> " + data));
        sleep(50);
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConnectableObservableExample demo = new ConnectableObservableExample();
        demo.marbleDiagram();
    }

}
