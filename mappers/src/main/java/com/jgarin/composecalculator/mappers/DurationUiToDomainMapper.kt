package com.jgarin.composecalculator.mappers

import com.jgarin.composecalculator.mappers.base.BaseMapper
import com.jgarin.composecalculator.models.DurationDomain
import com.jgarin.composecalculator.uimodels.DurationUi

class DurationUiToDomainMapper : BaseMapper<DurationUi, DurationDomain>() {

    override fun map(input: DurationUi): DurationDomain {
        return DurationDomain(
            id = input.id,
            hours = input.hours,
            minutes = input.minutes,
        )
    }
}