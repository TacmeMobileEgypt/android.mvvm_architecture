package com.mte.infrastructurebase.form_view.validationRules

import com.mte.infrastructurebase.forms.interfaces.IRule

class Required(val message: String?) : IRule<String> {

    override fun validate(value: String?): String? {

        return if(!value.isNullOrEmpty() && !value.isNullOrBlank())
            null
        else
            message

    }

}