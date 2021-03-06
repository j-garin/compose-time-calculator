package com.jgarin.composecalculator.repository

import com.jgarin.composecalculator.models.DurationDomain
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

internal class InMemoryDataRepository : DataRepository {

    private val data = ArrayList<DurationDomain>()
    private val invalidateAction = MutableSharedFlow<Unit>()
    private val scope = CoroutineScope(Dispatchers.Unconfined + SupervisorJob())
    private val items = invalidateAction.onSubscription {
        val count = invalidateAction.subscriptionCount.value
        if (count == 1) {
            emit(Unit)
        }
    }.map { loadData() }
        .shareIn(scope, replay = 1, started = SharingStarted.WhileSubscribed())


    override suspend fun creteItem(hours: Int, minutes: Int) = withContext(Dispatchers.IO) {
        delay(300) // emulate backend delay
        val item = DurationDomain(
            id = System.currentTimeMillis(),
            hours = hours,
            minutes = minutes,
        )
        data.add(item)
        invalidate()
    }

    override suspend fun readItems(): Flow<List<DurationDomain>> = items

    override suspend fun updateItem(item: DurationDomain) = withContext(Dispatchers.IO) {
        delay(500) // emulate backend delay
        val index = data.indexOfFirst { it.id == item.id }
        data[index] = item // let it throw
        invalidate()
    }

    override suspend fun deleteItem(id: Long) = withContext(Dispatchers.IO) {
        delay(200) // emulate backend delay
        data.removeAll { it.id == id }
        invalidate()
    }

    private suspend fun invalidate() = invalidateAction.emit(Unit)

    private suspend fun loadData(): List<DurationDomain> {
        delay(500)
        return data
    }
}