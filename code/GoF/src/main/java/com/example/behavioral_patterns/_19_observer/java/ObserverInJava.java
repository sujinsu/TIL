package com.example.behavioral_patterns._19_observer.java;

import java.util.Observable;
import java.util.Observer;


/**
 * Observer : interface
 * Observable : class
 */
public class ObserverInJava {

    static class User implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            System.out.println(arg);
        }
    }

    /**
     * setChanged 되어야 알림 받을 수 있음 등 >> Deprecate 이유
     */
    static class Subject extends Observable {
        public void add(String message) {
            setChanged();
            notifyObservers(message);
        }
    }

    public static void main(String[] args) {
        Subject subject = new Subject();
        User user = new User();
        subject.addObserver(user);
        subject.add("Hello Java, Observer");
    }

}
