package com.mte.infrastructurebase.custom_form

import com.mte.infrastructurebase.forms.interfaces.IRule

class FormValidationError(val messages  : List<String?>, val  formControlResID : Int)