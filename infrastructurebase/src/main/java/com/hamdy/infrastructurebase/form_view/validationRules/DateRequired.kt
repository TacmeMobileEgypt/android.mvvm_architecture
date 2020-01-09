package com.hamdy.infrastructurebase.form_view.validationRules

import com.hamdy.infrastructurebase.forms.interfaces.IRule
import java.util.*

class DateRequired(val message: String?) : IRule<Date> {

    override fun validate(value: Date?): String? {

        return if(value != null)
            null
        else
            message

    }

}