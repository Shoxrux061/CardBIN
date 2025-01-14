package com.shoxrux.cardbin.core

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend inline fun <S, reified E> parseResponse(
    dispatcher: CoroutineDispatcher, crossinline apiCall: suspend () -> Response<S?>
): ResultWrapper<S?, E> {
    return withContext(dispatcher) {
        try {
            val resp = apiCall.invoke()
            if (resp.isSuccessful) {
                ResultWrapper.Success(resp.code(), resp.body())
            } else {
                try {
                    if (resp.errorBody() != null) {
                        val error = Gson().fromJson(resp.errorBody()!!.string(), E::class.java)
                        ResultWrapper.Error(code = resp.code(), error)
                    } else {
                        ResultWrapper.NetworkError(resp.code())
                    }
                } catch (e: HttpException) {
                    val code = e.code()
                    val errorResponse =
                        Gson().fromJson(e.response()?.errorBody()?.string(), E::class.java)
                    ResultWrapper.Error(code, errorResponse)
                } catch (e: Exception) {
                    ResultWrapper.NetworkError(resp.code())
                }
            }
        } catch (throwable: Throwable) {
            when (throwable) {

                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse =
                        Gson().fromJson(throwable.response()?.errorBody()?.string(), E::class.java)
                    ResultWrapper.Error(code, errorResponse)
                }

                else -> {
                    ResultWrapper.Error(null, null)
                }
            }
        }
    }
}
