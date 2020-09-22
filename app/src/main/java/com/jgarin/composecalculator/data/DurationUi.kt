package com.jgarin.composecalculator.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DurationUi(val id: Long, val hours: Int, val minutes: Int): MainScreenListItem, Parcelable