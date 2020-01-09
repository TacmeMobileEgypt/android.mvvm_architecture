package com.hamdy.infrastructurebase.form_view.validationRules

import android.webkit.URLUtil
import com.hamdy.infrastructurebase.forms.interfaces.IRule

class ValidUrl(val message: String?) : IRule<String> {

    override fun validate(value: String?): String? {


        return if( URLUtil.isValidUrl(value))
            null
        else
            message

    }

}