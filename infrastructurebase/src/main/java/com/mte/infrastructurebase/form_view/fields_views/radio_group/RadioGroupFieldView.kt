package com.mte.infrastructurebase.form_view.fields_views.radio_group

import android.content.Context
import android.util.AttributeSet
import android.widget.*
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.forms.interfaces.IRule

import java.lang.Exception

open class RadioGroupFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : RadioGroup(context, attributeSet) , IFieldView<String?> {


    var checkOptions: List<CheckOptions>? = null

    var onChangeListener: OnChangeListener? = null

    private var attrChange: InverseBindingListener? = null

    var rules :  List<IRule<String>>? = null

    val validationMessages: ArrayList<String>? = ArrayList()


    init {
        init()
    }

    private fun init() {

        setOnCheckedChangeListener(object : OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
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
           check(getCheckedId(text))
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }

    override fun getValue(): String? {
        return getCheckValue(checkedRadioButtonId)
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        this.attrChange = attrChange
    }


    fun getCheckValue(checkedRadioButtonId: Int): String? {
        var checkValue : String? = null

        checkOptions?.forEach {
            if(checkedRadioButtonId == it.id)
                checkValue = it.value
        }
        return checkValue
    }

    fun getCheckedId(text: String?): Int {

        var id = 0

        checkOptions?.forEach {
            if(text?.equals(it.value) == true)
                id = it.id
        }

        return id
    }

    override fun setFormControl(formControl: IFormControl?) {}


}