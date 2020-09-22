package com.jgarin.composecalculator.repository

import com.jgarin.composecalculator.models.DurationDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class InMemoryDataRepository : DataRepository {

    private val data = ArrayList<DurationDomain>()
    private val channel = Channel<List<DurationDomain>>()

    override suspend fun creteItem(hours: Int, minutes: Int) = withContext(Dispatchers.IO) {
        delay(300) // emulate backend delay
        val item = DurationDomain(
            id = System.currentTimeMillis(),
            hours = hours,
            minutes = minutes,
        )
        data.add(item)
        channel.send(data)
    }

    override fun readItems(): Channel<List<DurationDomain>> {
        return channel
    }

    override suspend fun updateItem(item: DurationDomain) = withContext(Dispatchers.IO) {
        delay(500) // emulate backend delay
        val index = data.indexOfFirst { it.id == item.id }
        data[index] = item // let it throw
        channel.send(data)
    }

    override suspend fun deleteItem(id: Long) = withContext(Dispatchers.IO) {
        delay(200) // emulate backend delay
        data.removeAll { it.id == id }
        channel.send(data)
    }
}