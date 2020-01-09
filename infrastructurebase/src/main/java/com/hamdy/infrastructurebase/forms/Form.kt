package com.hamdy.infrastructurebase.forms

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.databinding.DefaultFormLayoutBinding
import com.hamdy.infrastructurebase.forms.base.BaseField
import com.hamdy.infrastructurebase.forms.interfaces.ValidationHandler

class Form(
    private val context: Context,
    private val container: ViewGroup,
    private val savedInstanceState: Bundle?,
    private val fields: ArrayList<BaseField<*, *>> = ArrayList(),
    title: String? = null
) {


    private var formContainer: ViewGroup? = null
    private val fieldsMap = HashMap<String, BaseField<*, *>>()
    private val validationFields: ArrayList<BaseField<*, *>> = ArrayList()
    protected lateinit var dataBinding: DefaultFormLayoutBinding

    private fun create() {

        dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.default_form_layout,
            container,
            true
        )

        formContainer = dataBinding.defaultFormContainer

        createFields()
    }

    private fun createFields() {
        fields.forEach {
            addFieldToForm(it)
        }
    }

    fun build() {
        create()
    }

    fun validate(validationHandler: ValidationHandler) {
        validationFields.clear()
        fields.forEach {
            if (!it.isValid())
                validationFields.add(it)
        }
        if (validationFields.size == 0)
            validationHandler.onSuccess()
        else
            validationHandler.onFailure(validationFields)
    }

    fun <F : BaseField<*, *>> getFieldsByTag(tag: String?): F? {
        return if (fieldsMap.get(tag) != null) fieldsMap.get(tag) as F else null
    }

    /*Add Field*/
    private fun addFieldToForm(field: BaseField<*, *>, postion: Int = -1) {

        if (!field.tag.isNullOrEmpty())
            fieldsMap.put(field.tag!!, field)

        var view = field.onCreateView(LayoutInflater.from(context), null, savedInstanceState)

        if (postion > -1)
            formContainer?.addView(view, postion)
        else
            formContainer?.addView(view, postion)

        field.position = formContainer?.indexOfChild(view) ?: -1
    }

    fun addField(field: BaseField<*, *>, postion: Int = -1) {

        fields.add(field)
        addFieldToForm(field)
    }

    /*Delete Field*/
    fun deleteField(tag: String) {
        fieldsMap[tag]?.view?.let {
            (it?.parent as ViewGroup).removeView(it)
        }

        (fields as ArrayList).remove(getFieldsByTag<BaseField<*, *>>(tag))
        fieldsMap.remove(tag)
    }

    fun replaceField(tag: String, baseField: BaseField<*, *>?, pos: Int?) {
        if(baseField == null) return
        deleteField(tag)
        addField(baseField , pos ?: -1)
    }


}