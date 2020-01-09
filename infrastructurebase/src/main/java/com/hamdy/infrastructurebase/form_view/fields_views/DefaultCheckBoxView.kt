package com.hamdy.infrastructurebase.form_view.fields_views

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.CompoundButton
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.databinding.CheckBoxLayoutBinding
import com.hamdy.infrastructurebase.databinding.EditTextViewLayoutBinding
import com.hamdy.infrastructurebase.form_view.DefaultLayoutFieldView

open class DefaultCheckBoxView(
    context: Context,
    attributeSet: AttributeSet? = null
) : CheckBox(context, attributeSet) {


    var model: ((String?) -> Unit)? = null
        set(value) {
            field = value
        }


     fun init() {

       setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//                value = isChecked.toString()
            }
        })
    }


}