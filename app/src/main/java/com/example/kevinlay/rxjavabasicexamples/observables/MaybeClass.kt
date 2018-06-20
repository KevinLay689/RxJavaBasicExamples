package com.example.kevinlay.rxjavabasicexamples.observables

import io.reactivex.Maybe

/**
 * Created by kevinlay on 6/19/18.
 */
class MaybeClass(val name: String) {

    fun getInfo(): Maybe<String> {
        return Maybe.create<String>({emitter ->
            when {
                name.length < 5  -> emitter.onSuccess(name)
                name.length > 10 -> emitter.onError(Throwable("Name was too long"))
                else -> emitter.onComplete()
            }
        })
    }
}