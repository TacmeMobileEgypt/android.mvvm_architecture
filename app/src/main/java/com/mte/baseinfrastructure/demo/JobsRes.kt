package com.mte.baseinfrastructure.demo

import com.mte.baseinfrastructure.network.DemoResponseModel

class JobsRes : DemoResponseModel(){

    val data : List<JobModel>? = null

    override fun getSuccess(): Any? {
        return data
    }
}