package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.repository.DataRepository
import com.jgarin.composecalculator.data.DurationItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

class ReadItemsUseCase(private val repo: DataRepository) {

    operator fun invoke(): Channel<List<DurationItem>> {
        return repo.readItems()
    }
}