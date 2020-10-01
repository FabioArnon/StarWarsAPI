package com.example.starwarsapi.application.xt

import androidx.lifecycle.MutableLiveData
import com.example.starwarsapi.presentation.base.BaseViewModel
import com.example.starwarsapi.presentation.base.ViewModelStatusEnum
import com.example.starwarsapi.presentation.base.ViewState
import com.example.starwarsapi.service.Result
import org.koin.core.inject
import org.koin.core.parameter.parametersOf
import retrofit2.Response
import kotlin.math.ceil
import kotlin.math.roundToInt

suspend fun <T> safeAppCall(call: suspend () -> Response<T>): Result<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            Result.Success(response.body()!!)
        } else Result.Failure(Throwable("Erro ${response.code()} ${response.message()}"))
    } catch (e: Exception) {
        Result.Failure(e)
    }
}

fun pages(count: Int): Int = ceil(count.div(10.0)).roundToInt()


fun <T> MutableLiveData<ViewState<T, ViewModelStatusEnum>>.postSuccess(data: T) {
    this.postValue(
        ViewState(
            data,
            ViewModelStatusEnum.SUCCESS
        )
    )
}

fun <T> MutableLiveData<ViewState<T, ViewModelStatusEnum>>.postError(throwable: Throwable) {
    this.postValue(
        ViewState(
            error = throwable,
            status = ViewModelStatusEnum.ERROR
        )
    )
}

fun <T> MutableLiveData<ViewState<T, ViewModelStatusEnum>>.postLoading() {
    this.postValue(
        ViewState(status = ViewModelStatusEnum.LOADING)
    )
}

fun <T> MutableLiveData<ViewState<T, ViewModelStatusEnum>>.postStatus(viewModelStatusEnum: ViewModelStatusEnum) {
    this.postValue(
        ViewState(status = viewModelStatusEnum)
    )
}

inline fun <reified I> BaseViewModel.interactor() = inject<I> { parametersOf(scope) }