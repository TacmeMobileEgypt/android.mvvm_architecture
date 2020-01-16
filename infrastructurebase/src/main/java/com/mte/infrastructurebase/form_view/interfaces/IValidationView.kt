package com.mte.infrastructurebase.form_view.interfaces

interface IValidationView {
    fun resetValidationView()
    fun showValidateError()
    fun getValidationMessage() : String?
    fun setMessage(validationMessage: String?)
}