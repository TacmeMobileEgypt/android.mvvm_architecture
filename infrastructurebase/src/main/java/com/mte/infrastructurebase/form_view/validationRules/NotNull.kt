package com.mte.infrastructurebase.form_view.validationRules

import com.mte.infrastructurebase.forms.interfaces.IRule

class NotNull(val message: String?) : IRule<Any?> {

    override fun validate(value: Any?): String? {

        return if(value == null)
            message
        else
            null

    }

}