package com.hamdy.infrastructurebase.forms.fields.attachments

interface OnAttachAction {

    fun editAttachmentClick(pos : Int , attachItemModel: AttachItemModel)
    fun deleteAttachmentClick(pos : Int ,  attachItemModel: AttachItemModel)
}