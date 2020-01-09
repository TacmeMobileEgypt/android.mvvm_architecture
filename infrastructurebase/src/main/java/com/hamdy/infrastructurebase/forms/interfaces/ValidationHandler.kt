package com.hamdy.infrastructurebase.forms.interfaces

import com.hamdy.infrastructurebase.forms.base.BaseField

interface ValidationHandler {

    fun onSuccess();
    fun onFailure(fields: ArrayList<BaseField<* , *>>);
}