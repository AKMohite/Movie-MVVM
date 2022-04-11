package com.mak.telflix.domain.paginate

import com.mak.telflix.domain.common.ResultWrapper

/**
 * */
class Paginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> ResultWrapper<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): IPaginator<Key, Item> {

    private var currentKey: Key = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if (isMakingRequest) return

        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        when(result) {
            is ResultWrapper.Error -> {
                onError(result.throwable)
                onLoadUpdated(false)
            }
            is ResultWrapper.Success -> {
                currentKey = getNextKey(result.data)
                onSuccess(result.data, currentKey)
                onLoadUpdated(false)
            }
        }
    }

    override fun reset() {
        currentKey = initialKey
    }
}

interface IPaginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}