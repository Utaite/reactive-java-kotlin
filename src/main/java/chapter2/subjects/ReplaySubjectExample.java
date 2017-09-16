package chapter2.subjects;

import io.reactivex.subjects.ReplaySubject;


public class ReplaySubjectExample {

    public void marbleDiagram() {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(data -> System.out.println("Subscriber #1 -> " + data));
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscriber #2 -> " + data));
        subject.onNext("5");
        subject.onComplete();
    }

    public static void main(String[] args) {
        ReplaySubjectExample demo = new ReplaySubjectExample();
        demo.marbleDiagram();
    }

}
