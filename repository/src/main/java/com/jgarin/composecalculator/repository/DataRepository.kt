package com.jgarin.composecalculator.repository

import com.jgarin.composecalculator.models.DurationDomain
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    suspend fun creteItem(hours: Int, minutes: Int)

    suspend fun readItems(): Flow<List<DurationDomain>>

    suspend fun updateItem(item: DurationDomain)

    suspend fun deleteItem(id: Long)
}