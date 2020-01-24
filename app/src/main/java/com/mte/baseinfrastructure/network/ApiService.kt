package com.mte.baseinfrastructure.network

import androidx.lifecycle.LiveData
import com.mte.baseinfrastructure.demo.JobsRes
import com.mte.infrastructurebase.data.source.remote.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path



interface ApiService {


    @GET("${ApiConstants.BASE_URL}/show/company_jobs/{companyId}")
    fun callGetCompanyJobs(@Path("companyId") companyId: String): LiveData<ApiResponse<JobsRes?>>

}