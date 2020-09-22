package com.jgarin.composecalculator.mappers

import com.jgarin.composecalculator.base.BaseMapper
import com.jgarin.composecalculator.data.DurationUi
import com.jgarin.composecalculator.models.DurationDomain

class DurationUiToDomainMapper : BaseMapper<DurationUi, DurationDomain>() {

    override fun map(input: DurationUi): DurationDomain {
        return DurationDomain(
            id = input.id,
            hours = input.hours,
            minutes = input.minutes,
        )
    }
}