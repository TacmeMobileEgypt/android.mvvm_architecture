package com.hamdy.infrastructurebase.form_view.interfaces


interface ValidationFieldViewHandler {

    fun onSuccess()
    fun onFailure(fields: ArrayList<IValidationView?>)
}