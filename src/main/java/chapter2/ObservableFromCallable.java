package chapter2;

import io.reactivex.Observable;
import java.util.concurrent.Callable;


public class ObservableFromCallable {

    public void basic() {
        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "Hello Callable";
        };

        Observable<String> source = Observable.fromCallable(callable);
        source.subscribe(System.out::println);
    }

    public void withoutLambda() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Hello Callable";
            }
        };

        Observable<String> source = Observable.fromCallable(callable);
        source.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        ObservableFromCallable demo = new ObservableFromCallable();
        demo.basic();
        demo.withoutLambda();
    }

}
