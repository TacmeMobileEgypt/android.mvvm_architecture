package com.mte.infrastructurebase.data.source.remote



abstract class BaseResponseModel : ErrorRes(){

   abstract fun getSuccess() : Any?

}