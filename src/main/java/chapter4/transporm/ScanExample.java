package chapter4.transporm;

import common.Log;
import io.reactivex.Observable;

public class ScanExample {

    public void marbleDiagram() {
        String[] balls = {"1","3","5"};
        Observable<String> source = Observable.fromArray(balls)
                .scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source.subscribe(Log::it);
    }

    public static void main(String[] args) {
        ScanExample demo = new ScanExample();
        demo.marbleDiagram();
    }

}
