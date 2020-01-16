package com.mte.infrastructurebase.forms.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract open class BaseForm<D : ViewDataBinding>(protected var title : String? = null) {

    protected lateinit var dataBinding: D

    protected var fields : List<BaseField<* , *>> = ArrayList()

    abstract val layoutRes: Int

    open fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)

        createFields(inflater , container , savedInstanceState)

        return dataBinding.root
    }

    abstract fun createFields(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?)

}