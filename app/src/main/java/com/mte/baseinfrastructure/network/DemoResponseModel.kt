package com.mte.baseinfrastructure.network

import com.mte.infrastructurebase.data.source.remote.BaseResponseModel


abstract class DemoResponseModel : BaseResponseModel(){

   override val error: String?
      get() = null
}