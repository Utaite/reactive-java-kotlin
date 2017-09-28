package chapter7;

import common.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;


public class ExceptionHandling {

    public void cannotCatch() {
        Observable<String> source = Observable.create(
                (ObservableEmitter<String> emitter) -> {
                    emitter.onNext("1");
                    emitter.onError(new Exception("Some Error"));
                    emitter.onNext("3");
                    emitter.onComplete();
                }
        );

        try {
            source.subscribe(Log::it);
        } catch (Exception e) {
            Log.it(e.getMessage());
        }
    }

    public void onErrorReturn() {
        String[] grades = {"70", "88", "$100", "93", "83"};

        Observable<Integer> source = Observable.fromArray(grades)
                .map(data -> Integer.parseInt(data))
                .onErrorReturn(e -> {
                    if (e instanceof NumberFormatException) {
                        e.printStackTrace();
                    }
                    return -1;
                });

        source.subscribe(data -> {
            if (data < 0) {
                Log.it("Wrong Data found!!");
                return;
            }
            Log.it("Grade is " + data);
        });
    }

    public void onError() {
        String[] grades = {"70", "88", "$100", "93", "83"};

        Observable<Integer> source = Observable.fromArray(grades)
                .map(data -> Integer.parseInt(data));
        source.subscribe(
                data -> Log.it("Grade is " + data),
                e -> {
                    if (e instanceof NumberFormatException) {
                        e.printStackTrace();
                    }
                    Log.it("Wrong Data found!!");
                }
        );
    }

    public void onErrorResumeNext() {
        String[] salesData = {"100", "200", "A300"};
        Observable<Integer> onParseError = Observable.defer(() -> {
           Log.it("send email to administrator");
           return Observable.just(-1);
        }).subscribeOn(Schedulers.io());

        Observable<Integer> source = Observable.fromArray(salesData)
                .map(Integer::parseInt)
                .onErrorResumeNext(onParseError);

        source.subscribe(data -> {
            if (data < 0) {
                Log.it("Wrong Data found!!");
                return;
            }
            Log.it("Sales data : " + data);
        });
    }

    public static void main(String[] args) {
        ExceptionHandling demo = new ExceptionHandling();
        // demo.cannotCatch();
        // demo.onErrorReturn();
        // demo.onError();
        demo.onErrorResumeNext();
    }

}
