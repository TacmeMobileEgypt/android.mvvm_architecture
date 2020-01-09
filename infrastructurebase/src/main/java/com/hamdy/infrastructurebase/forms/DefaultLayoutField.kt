package com.hamdy.infrastructurebase.forms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hamdy.infrastructurebase.forms.base.BaseField
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.databinding.DefaultFieldLayoutBinding
import com.hamdy.infrastructurebase.forms.interfaces.IRule

abstract class DefaultLayoutField<D : ViewDataBinding , T>(
    val title: String? = null,
    tag: String?,
    rules: List<IRule<T>>?
) : BaseField<D , T>(tag , rules) {

    private var defaultDataBinding: DefaultFieldLayoutBinding? = null

    private val TAG: String = "DefaultLayoutField"


    override var msgError : String?= null
        set(value) {
            field = value
            defaultDataBinding?.msg = value
            field = value
        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        defaultDataBinding = DataBindingUtil
            .inflate<DefaultFieldLayoutBinding>(inflater,
                R.layout.default_field_layout, container, true)

        defaultDataBinding?.defaultFieldContainer?.addView(super.onCreateView(inflater, container, savedInstanceState))

        view = defaultDataBinding?.root

        init()

        return view
    }

    override fun init() {
        defaultDataBinding?.title = title
    }


    override fun showValidateError() {
        msgError = msgError
    }


    override fun resetMessageError() {
        msgError = null
    }

}