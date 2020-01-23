package com.mte.infrastructurebase.form_view.validationRules

import com.mte.infrastructurebase.forms.interfaces.IRule

class RequiredBool(val message: String?) : IRule<Boolean> {

    override fun validate(value: Boolean?): String? {

        return if(value == null || !value)
            message
        else
            null

    }

}