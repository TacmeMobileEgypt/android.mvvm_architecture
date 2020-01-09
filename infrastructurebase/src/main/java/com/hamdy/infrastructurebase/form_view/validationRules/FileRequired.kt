package com.hamdy.infrastructurebase.form_view.validationRules

import com.hamdy.infrastructurebase.forms.interfaces.IRule
import java.io.File

class FileRequired(val message: String?) : IRule<File> {

    override fun validate(value: File?): String? {

        return if(value != null)
            null
        else
            message

    }

}