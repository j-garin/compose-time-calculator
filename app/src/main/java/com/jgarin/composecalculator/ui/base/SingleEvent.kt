package com.jgarin.composecalculator.ui.base

class SingleEvent<T>(private val data: T) {

    private var wasSeen: Boolean = false

    val event: T?
        get() = if (wasSeen) null else {
            wasSeen = true
            data
        }

    fun peek(): T = data
}