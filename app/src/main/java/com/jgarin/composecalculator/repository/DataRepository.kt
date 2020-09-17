package com.jgarin.composecalculator.repository

import com.jgarin.composecalculator.data.DurationItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    suspend fun creteItem(hours: Int, minutes: Int)

    fun readItems(): Channel<List<DurationItem>>

    suspend fun updateItem(item: DurationItem)

    suspend fun deleteItem(id: Long)
}