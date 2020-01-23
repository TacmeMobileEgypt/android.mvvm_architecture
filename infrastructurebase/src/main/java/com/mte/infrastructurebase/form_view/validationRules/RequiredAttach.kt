package com.mte.infrastructurebase.form_view.validationRules

import com.mte.infrastructurebase.forms.fields.attachments.AttachItemModel
import com.mte.infrastructurebase.forms.interfaces.IRule

class RequiredAttach(val message: String?) : IRule<AttachItemModel> {

    override fun validate(value: AttachItemModel?): String? {

        return if(value?.fileUri == null)
            message
        else
            null


    }

}