package com.jgarin.composecalculator.repository

import com.jgarin.composecalculator.models.DurationDomain
import kotlinx.coroutines.flow.SharedFlow

interface DataRepository {

    suspend fun creteItem(hours: Int, minutes: Int)

    suspend fun readItems(): SharedFlow<List<DurationDomain>>

    suspend fun updateItem(item: DurationDomain)

    suspend fun deleteItem(id: Long)
}