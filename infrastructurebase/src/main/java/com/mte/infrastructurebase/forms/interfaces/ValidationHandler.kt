package com.mte.infrastructurebase.forms.interfaces

import com.mte.infrastructurebase.forms.base.BaseField

interface ValidationHandler {

    fun onSuccess();
    fun onFailure(fields: ArrayList<BaseField<* , *>>);
}