package chapter7.flowcontrol;

import common.CommonUtils;
import common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class DebounceExample {

    public void marbleDiagram() {
        String[] data = {"1", "2", "3", "5"};
        Observable<String> source = Observable.concat(
                Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[0]),
                Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[1]),
                Observable.timer(100L, TimeUnit.MILLISECONDS).map(i -> data[2]),
                Observable.timer(300L, TimeUnit.MILLISECONDS).map(i -> data[3])
        ).debounce(200L, TimeUnit.MILLISECONDS);

        source.subscribe(Log::it);
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        DebounceExample demo = new DebounceExample();
        demo.marbleDiagram();
    }

}
