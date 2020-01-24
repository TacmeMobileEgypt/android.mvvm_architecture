package com.mte.baseinfrastructure.demo

import com.mte.baseinfrastructure.demo.JobModel
import com.mte.baseinfrastructure.network.BaseResponseModel

open class JobsRes : BaseResponseModel(){

    val data : List<JobModel>? = null

    override fun getSuccess(): Any? {
        return data
    }
}