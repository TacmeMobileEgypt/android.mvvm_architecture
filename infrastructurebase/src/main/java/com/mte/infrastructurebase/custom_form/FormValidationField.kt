package com.mte.infrastructurebase.custom_form

import com.mte.infrastructurebase.forms.interfaces.IRule

class FormValidationField<T> (val value  : T, val  formControlResID : Int, val rules : List<IRule<T>> ? = null)