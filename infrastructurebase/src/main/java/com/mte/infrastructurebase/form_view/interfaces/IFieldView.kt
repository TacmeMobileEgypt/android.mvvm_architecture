package com.mte.infrastructurebase.form_view.interfaces

import androidx.databinding.InverseBindingListener

interface IFieldView<T> {
    fun isValid(): Boolean
    fun getValidationMessage(): String?
    fun setValue(value: T?)
    fun getValue(): T?
    fun setAttrChange(attrChange: InverseBindingListener)
    fun setFormControl(formControl: IFormControl?)
}