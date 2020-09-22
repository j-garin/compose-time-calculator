package com.jgarin.composecalculator.repository.di

import com.jgarin.composecalculator.repository.DataRepository
import com.jgarin.composecalculator.repository.InMemoryDataRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<DataRepository> { InMemoryDataRepository() }
}