package com.hamdy.infrastructurebase.form_view.interfaces

import android.view.View

interface IFormControl {
    fun isValid(): Boolean
    fun getTag(): String?
    fun getView(): View?
    fun getValidationView(): IValidationView?
}