package com.example.starwarsapi.utils

import com.example.starwarsapi.presentation.base.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class DispatcherProviderTest : DispatcherProvider() {
    override val io: CoroutineContext
            by lazy { Dispatchers.Unconfined}
    override val ui: CoroutineContext
            by lazy {Dispatchers.Unconfined}
}