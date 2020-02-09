package com.mte.infrastructurebase.custom_form

import com.mte.infrastructurebase.forms.interfaces.IRule

class FormValidation(){


    private val fields  = ArrayList<FormValidationField<*>>()
    private val validationFields: ArrayList<FormValidationError?> = ArrayList()

    fun <T> addField(value  : T , formControlResID : Int , rules : List<IRule<T>> ? = null) : FormValidation{

        fields.add(
            FormValidationField<T>(
                value = value,
                formControlResID = formControlResID,
                rules = rules
            )
        )

        return this
    }


    fun validate(validationHandler: FormValidationHandler) {

        validationFields.clear()

        fields.forEach {

            val messages = isValid(it)

            if (messages.size > 0)
                validationFields.add(FormValidationError(messages , it.formControlResID))
        }

        if (validationFields.size == 0)
            validationHandler.onSuccess()
        else
            validationHandler.onFailure(validationFields)
    }

   private fun <T>isValid(formModel: FormValidationField<T>): ArrayList<String?> {


        val  validationMessages = ArrayList<String?>()

        formModel.rules?.forEach {
            val message = it.validate(formModel.value)
            if (message != null)
                validationMessages?.add(message)
        }

        return validationMessages

    }



}