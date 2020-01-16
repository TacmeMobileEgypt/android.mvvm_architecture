package com.mte.infrastructurebase.attachments

import com.mte.infrastructurebase.forms.fields.attachments.AttachItemModel

interface OnAttachmentSelectedListener {

    fun onAttachmentsSelected(attachments: List<AttachItemModel?>)
}