package com.example.kevinlay.rxjavabasicexamples.observables;

import io.reactivex.Completable;

/**
 * Created by kevinlay on 6/17/18.
 */

public class CompletableClass {
    int age = 25;

    public Completable getInfo() {
        return Completable.create(emitter -> {
            if (age > 25) {
                emitter.onComplete();
            } else {
                emitter.onError(new Throwable("Age not above 25"));
            }
        });
    }
}
