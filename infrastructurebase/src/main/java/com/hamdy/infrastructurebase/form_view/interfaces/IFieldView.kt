package com.hamdy.infrastructurebase.form_view.interfaces

import androidx.databinding.InverseBindingListener

interface IFieldView {
    fun isValid(): Boolean
    fun getValidationMessage(): String?
    fun setValue(text: String?)
    fun getValue(): String?
    fun setAttrChange(attrChange: InverseBindingListener)
}