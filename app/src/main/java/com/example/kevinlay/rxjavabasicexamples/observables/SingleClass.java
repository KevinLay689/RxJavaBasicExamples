package com.example.kevinlay.rxjavabasicexamples.observables;

import io.reactivex.Single;

/**
 * Created by kevinlay on 6/17/18.
 */

public class SingleClass {
    String name = "Kevin";
    int age = 25;

    public Single<String> getInfo() {
        return Single.just(name + age);
    }

    public Single<String> getModifiedInfo() {
        return Single.just(name)
                .map(n -> {
                    age = age + 5;
                    return n + age;
                });
    }
}
