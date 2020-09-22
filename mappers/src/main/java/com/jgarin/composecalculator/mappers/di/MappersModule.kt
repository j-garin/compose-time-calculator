package com.jgarin.composecalculator.mappers.di

import com.jgarin.composecalculator.mappers.DurationDomainToUiMapper
import com.jgarin.composecalculator.mappers.DurationUiToDomainMapper
import org.koin.dsl.module

val mappersModule = module {

    factory { DurationDomainToUiMapper() }
    factory { DurationUiToDomainMapper() }
}