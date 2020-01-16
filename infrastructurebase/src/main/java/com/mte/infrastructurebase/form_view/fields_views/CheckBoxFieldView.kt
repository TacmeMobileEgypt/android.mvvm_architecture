package com.mte.infrastructurebase.form_view.fields_views

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IRule
import java.lang.Exception

open class CheckBoxFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : CheckBox(context, attributeSet) , IFieldView {


    private var attrChange: InverseBindingListener? = null

    var rules :  List<IRule<String>>? = null

    val validationMessages: ArrayList<String>? = ArrayList()


    init {
        init()
    }

    private fun init() {

        setOnCheckedChangeListener(object : OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                attrChange?.onChange()
            }

        })

    }

    override fun isValid(): Boolean {

        validationMessages?.clear()

        rules?.forEach {
            val message = it.validate(getValue())
            if (message != null)
                validationMessages?.add(message)
        }

        return validationMessages?.size == 0
    }

    override fun getValidationMessage(): String? {
       return validationMessages?.get(0)
    }

    override fun setValue(text: String?) {
        try {
            isChecked = text!!.toBoolean()
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }

    override fun getValue(): String? {
        return if(isChecked) isChecked.toString() else null
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        this.attrChange = attrChange
    }


}