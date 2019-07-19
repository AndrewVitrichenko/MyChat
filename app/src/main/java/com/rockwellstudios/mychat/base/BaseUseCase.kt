package com.rockwellstudios.mychat.base

abstract class BaseUseCase<T> {

    abstract fun execute() : T
}