package chapter2;

import io.reactivex.Observable;
import java.util.stream.IntStream;


public class ObservableFromArray {

    public void integerArray() {
        Integer[] arr = {100, 200, 300};
        Observable<Integer> source = Observable.fromArray(arr);
        source.subscribe(System.out::println);
    }

    public void intArray() {
        int[] intArray = {400, 500, 600};
        Observable<Integer> source = Observable.fromArray(toIntegerArray(intArray));
        source.subscribe(System.out::println);
    }

    public void intArrayWrong() {
        int[] intArray = {400, 500, 600};
        Observable.fromArray(intArray).subscribe(System.out::println);
    }

    private static Integer[] toIntegerArray(int[] intArray) {
        return IntStream.of(intArray).boxed().toArray(Integer[]::new);
    }

    public static void main(String[] args) {
        ObservableFromArray demo = new ObservableFromArray();
        demo.integerArray();
        demo.intArray();
        demo.intArrayWrong();
    }

}
