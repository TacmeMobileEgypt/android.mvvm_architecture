package com.mte.infrastructurebase.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

open class BaseViewModel : ViewModel() , KoinComponent{

    var activity: Activity? = null
    var context: Context? = null
    protected  var lifecycleOwner: LifecycleOwner? = null
    protected val fragment : BaseFragment<*>? = null

    fun init(lifecycleOwner: LifecycleOwner){
        this.lifecycleOwner = lifecycleOwner
    }



    fun addFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

       this.fragment?.addFragment(fragment , id , addToBackStack)
    }

    fun replaceFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        this.fragment?.replaceFragment(fragment , id , addToBackStack)

    }

    

}
