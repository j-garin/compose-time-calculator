package com.jgarin.composecalculator.usecase

import com.jgarin.composecalculator.repository.DataRepository
import com.jgarin.composecalculator.models.DurationDomain
import kotlinx.coroutines.channels.Channel

class ReadItemsUseCase(private val repo: DataRepository) {

    operator fun invoke(): Channel<List<DurationDomain>> {
        return repo.readItems()
    }
}