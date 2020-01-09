package com.hamdy.infrastructurebase.form_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.databinding.DefaultFieldLayoutBinding
import com.hamdy.infrastructurebase.form_view.interfaces.IFieldView
import com.hamdy.infrastructurebase.form_view.interfaces.IFormControl
import com.hamdy.infrastructurebase.form_view.interfaces.ILabelView
import com.hamdy.infrastructurebase.form_view.interfaces.IValidationView

 class FormControlView(
    context: Context,
    attributeSet: AttributeSet? = null) : LinearLayout(context , attributeSet) , IFormControl {


    private var validationView: IValidationView? = null
    private var fieldView: IFieldView? = null
    private var labelView: ILabelView? = null

    override fun onFinishInflate() {
        super.onFinishInflate()

        val count = childCount
        for (i in 0 until count){
            val child = getChildAt(i)
            when(child){
                is ILabelView -> {labelView = child}
                is IFieldView -> {fieldView = child}
                is IValidationView-> {validationView = child}
            }
        }

    }

     override fun isValid(): Boolean {
         validationView?.resetValidationView()

         if(fieldView?.isValid() == true)
             return true

         validationView?.setMessage(fieldView?.getValidationMessage())

        return false
     }

     override fun getTag(): String? {
         return  ""
     }

     override fun getView(): View? {
         return this
     }

     override fun getValidationView(): IValidationView? {
         return validationView
     }
 }