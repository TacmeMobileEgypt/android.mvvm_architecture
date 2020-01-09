package com.hamdy.infrastructurebase.attachments

import com.hamdy.infrastructurebase.forms.fields.attachments.AttachItemModel

interface OnAttachmentSelectedListener {

    fun onAttachmentsSelected(attachments: List<AttachItemModel?>)
}