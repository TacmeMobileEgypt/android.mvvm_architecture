package com.mte.infrastructurebase.form_view.fields_views

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.forms.interfaces.IRule

open class TextFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : EditText(context, attributeSet) , IFieldView<String?> {


    private var attrChange: InverseBindingListener? = null

     var rules :  List<IRule<String>>? = null

    val validationMessages: ArrayList<String>? = ArrayList()

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
       setText(text)
    }

    override fun getValue(): String? {
        return text.toString().trim()
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        this.attrChange = attrChange
    }

    override fun setFormControl(formControl: IFormControl?) {}

}