package com.mte.baseinfrastructure.demo

import androidx.lifecycle.LiveData
import com.mte.baseinfrastructure.network.ApiService
import com.mte.infrastructurebase.data.source.remote.*

class DemoRepo(val apiService: ApiService,
               val contextProviders: ContextProviders) {



    fun CallGetCompanyJobs(companyId : String) : LiveData<Resource<List<JobModel?>?>> {


        return object : NetworkBoundResource<List<JobModel?>?, JobsRes?>(contextProviders) {
            override fun saveCallResult(item: JobsRes?) {
                itemsData = item?.data
            }

            override fun getResult(): List<JobModel?>? {
                return itemsData
            }

            override fun createCall(): LiveData<ApiResponse<JobsRes?>> {
                return apiService.callGetCompanyJobs(companyId)
            }

        }.asLiveData()
    }
}