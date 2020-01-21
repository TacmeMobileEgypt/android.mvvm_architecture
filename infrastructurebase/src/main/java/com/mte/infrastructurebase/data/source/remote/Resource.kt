package com.mte.infrastructurebase.data.source.remote

import androidx.annotation.StringRes

data class Resource<out T>(var status: Status, val data: T? = null, var message: String? = null ,@StringRes var strRes : Int? = null ) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String? , data: T? = null): Resource<T> {
            return Resource(
                status = Status.ERROR,
                data = data,
                message = msg
            )
        }

        fun <T> error(@StringRes strRes : Int ,  data: T? = null): Resource<T> {
            return Resource(
                status =  Status.ERROR,
                data = data,
                strRes = strRes
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }

        fun <T> empty(): Resource<T> {
            return Resource(
                Status.EMPTY,
                null,
                null
            )
        }
    }
}