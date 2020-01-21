package com.mte.infrastructurebase.form_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.databinding.DefaultFieldLayoutBinding

abstract class DefaultLayoutFieldView(
    context: Context,
    attributeSet: AttributeSet? = null) : BaseFieldView<String>(context , attributeSet) {

    private lateinit var defaultDataBinding: DefaultFieldLayoutBinding

    override var msgError : String?= null
        set(value) {
            field = value
            defaultDataBinding?.msg = value
            field = value
        }

    override fun init() {
        defaultDataBinding = DataBindingUtil
            .inflate<DefaultFieldLayoutBinding>(
                LayoutInflater.from(context),
                R.layout.default_field_layout,
                null,
                false)

        defaultDataBinding?.title = title

    }


    override fun showValidateError() {
        msgError = msgError
    }


    override fun resetMessageError() {
        msgError = null
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        val chiild : View =  getChildAt(0)

        if(chiild == null) return

        removeView(chiild)

        defaultDataBinding.defaultFieldContainer.addView(chiild)

        addView(defaultDataBinding.root)

    }
}