package com.mte.baseinfrastructure.demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.mte.infrastructurebase.base.BaseViewModel

class DemoViewModel(val jobsRepo: DemoRepo) : BaseViewModel() {

    fun getList() {
        companyIdLD.value = "48"
    }


    private val companyIdLD = MutableLiveData<String>()

    val jobsDataLD = Transformations.switchMap(companyIdLD){
        jobsRepo.CallGetCompanyJobs(it)
    }


}