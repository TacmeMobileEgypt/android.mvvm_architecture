package com.mte.infrastructurebase.form_view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.forms.interfaces.IRule

abstract class BaseFieldView<T>(
    context: Context ,
    attributeSet: AttributeSet? = null) : FrameLayout(context , attributeSet) {

     var attrChange: InverseBindingListener? = null

    protected var typedArray: TypedArray? = null

    var position = -1

    var title : String? = null
    var tag : String? = null

    open var rules: List<IRule<T>>? = ArrayList<IRule<T>>()

    val validationMessages: ArrayList<String>? = ArrayList()

    open var value: T? = null
    set(value) {
        field = value
        attrChange?.onChange()
        onValueChanged(value)
    }

    abstract fun onValueChanged(value: T?)

    open var msgError : String?= null
        get() {
            if(validationMessages?.size == 0) return null
            return validationMessages?.get(0)
        }

    init {
        if(attributeSet != null){
             typedArray  = context.obtainStyledAttributes(attributeSet, R.styleable.form_styleable)
             title  = typedArray?.getString(R.styleable.form_styleable_title)
        }

        onCreateView()
    }

    fun onCreateView(){
        init()
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