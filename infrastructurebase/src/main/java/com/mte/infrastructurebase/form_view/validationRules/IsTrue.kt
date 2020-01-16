package com.mte.infrastructurebase.form_view.validationRules

import com.mte.infrastructurebase.forms.interfaces.IRule

class IsTrue(val message: String?) : IRule<String> {

    override fun validate(value: String?): String? {

        return if(!value.isNullOrEmpty() && !value.equals("false" , true))
            null
        else
            message

    }

}