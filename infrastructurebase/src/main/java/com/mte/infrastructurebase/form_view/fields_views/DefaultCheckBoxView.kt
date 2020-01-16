package com.mte.infrastructurebase.form_view.fields_views

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.CompoundButton
import com.mte.infrastructurebase.databinding.CheckBoxLayoutBinding
import com.mte.infrastructurebase.databinding.EditTextViewLayoutBinding

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