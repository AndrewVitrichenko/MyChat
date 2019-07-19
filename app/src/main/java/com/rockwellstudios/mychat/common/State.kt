package com.rockwellstudios.mychat.common

class State<D> private constructor(val status: Int, val data: D?, val error: Throwable?) {
    companion object {

        fun loading(): State {
            return State(Status.LOADING, null, null)
        }

        fun success(data: String): State {
            return State(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable): State {
            return State(Status.ERROR, null, error)
        }
    }
}
