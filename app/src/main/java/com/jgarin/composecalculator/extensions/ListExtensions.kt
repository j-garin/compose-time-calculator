package com.jgarin.composecalculator.extensions

fun <T> List<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}