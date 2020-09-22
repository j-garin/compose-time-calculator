package com.jgarin.composecalculator.mappers

import com.jgarin.composecalculator.mappers.base.BaseMapper
import com.jgarin.composecalculator.uimodels.DurationUi
import com.jgarin.composecalculator.models.DurationDomain

class DurationDomainToUiMapper : BaseMapper<DurationDomain, DurationUi>() {

    override fun map(input: DurationDomain): DurationUi {
        return DurationUi(
            id = input.id,
            hours = input.hours,
            minutes = input.minutes,
        )
    }
}