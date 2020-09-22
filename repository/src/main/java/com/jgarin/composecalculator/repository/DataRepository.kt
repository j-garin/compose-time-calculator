package com.jgarin.composecalculator.repository

import com.jgarin.composecalculator.models.DurationDomain
import kotlinx.coroutines.channels.Channel

interface DataRepository {

    suspend fun creteItem(hours: Int, minutes: Int)

    fun readItems(): Channel<List<DurationDomain>>

    suspend fun updateItem(item: DurationDomain)

    suspend fun deleteItem(id: Long)
}