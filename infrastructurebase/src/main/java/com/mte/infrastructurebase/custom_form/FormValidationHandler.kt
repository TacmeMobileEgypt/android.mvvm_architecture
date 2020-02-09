package com.mte.infrastructurebase.custom_form


interface FormValidationHandler {

    fun onSuccess()
    fun onFailure(fields: ArrayList<FormValidationError?>)
}