package com.mte.infrastructurebase.form_view.interfaces


interface ValidationFieldViewHandler {

    fun onSuccess()
    fun onFailure(fields: ArrayList<IFormControl?>)
}