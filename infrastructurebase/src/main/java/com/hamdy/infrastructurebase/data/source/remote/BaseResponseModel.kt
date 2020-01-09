package com.hamdy.infrastructurebase.data.source.remote



abstract class BaseResponseModel : ErrorRes(){

   abstract fun getSuccess() : Any?

}