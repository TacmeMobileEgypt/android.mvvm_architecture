package com.hamdy.infrastructurebase.data.source.remote

 
data class Resource<out T>(var status: Status, val data: T?, var message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg
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