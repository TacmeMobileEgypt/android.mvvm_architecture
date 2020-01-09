package com.hamdy.infrastructurebase.forms.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hamdy.infrastructurebase.forms.interfaces.IRule

abstract class BaseField<D : ViewDataBinding , T>(val tag : String? = null, open var rules: List<IRule<T>>? = ArrayList()) {

    protected var context: Context? = null
    var view: View? = null

    var position = -1

    protected  var dataBinding: D? = null
     val validationMessages: ArrayList<String>? = ArrayList()

    open var value: T? = null

    abstract val layoutRes: Int


    open var msgError : String?= null
        get() {
            if(validationMessages?.size == 0) return null
            return validationMessages?.get(0)
        }

    open fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)

        init()

        view = dataBinding?.root

        context = view?.context

        return view
    }

    abstract fun init()


    fun isValid(): Boolean {

        resetMessageError()

        validationMessages?.clear()

        rules?.forEach {
            val message = it.validate(value)
            if (message != null)
                validationMessages?.add(message)
        }

        return validationMessages?.size == 0
    }
    abstract fun showValidateError()
    open fun resetMessageError(){
        msgError = null
    }

}