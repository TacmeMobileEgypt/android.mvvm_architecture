package com.mte.baseinfrastructure.network


abstract class BaseResponseModel : ErrorRes(){

   abstract fun getSuccess() : Any?

}