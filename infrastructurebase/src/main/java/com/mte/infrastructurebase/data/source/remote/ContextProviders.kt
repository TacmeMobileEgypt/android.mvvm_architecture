package com.mte.infrastructurebase.data.source.remote

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


open class ContextProviders {
    open val Main: CoroutineContext = Dispatchers.Main
    open val IO: CoroutineContext = Dispatchers.IO

    companion object {

        fun getInstance() : ContextProviders {
            return  synchronized(this) {
                ContextProviders()
            }
        }
    }
}