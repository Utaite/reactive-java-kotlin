package chapter4;

import common.Log;
import io.reactivex.Observable;


public class RangeExample {

    public void forLoop() {
        Observable<Integer> source = Observable.range(1, 10)
                .filter(number -> number % 2 == 0);
        source.subscribe(Log::it);
    }

    public static void main(String[] args) {
        RangeExample demo = new RangeExample();
        demo.forLoop();
    }

}
