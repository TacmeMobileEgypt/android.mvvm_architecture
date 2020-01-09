package com.hamdy.infrastructurebase.forms.fields.attachments.doc_attachment

import android.app.Activity
import android.view.View
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.attachments.DocAttachments
import com.hamdy.infrastructurebase.attachments.OnAttachmentSelectedListener
import com.hamdy.infrastructurebase.databinding.DefaultDocAttachFieldBinding
import com.hamdy.infrastructurebase.forms.fields.attachments.AttachItemModel
import com.hamdy.infrastructurebase.forms.fields.attachments.BaseAttachmentsField
import com.hamdy.infrastructurebase.forms.fields.attachments.OnAttachAction
import com.hamdy.infrastructurebase.forms.interfaces.IRule
import java.io.File



 class DocAttachmentsField(
    activity: Activity,
    title: String,
    tag: String? = null,
    isEditable: Boolean = true,
    isSingleFile: Boolean = false,
    rules: List<IRule<File>>? = ArrayList(),
    model: ((File?) -> Unit)? = null,
    isMulti: Boolean = false

) : BaseAttachmentsField<DefaultDocAttachFieldBinding>(
     activity ,
     title ,
     tag ,
     isEditable ,
     isSingleFile ,
     rules ,
     model ,
     isMulti), OnAttachmentSelectedListener, OnAttachAction {


     private lateinit var adapter: DocAttachmentsAdapter


     init {
         baseAttachments = DocAttachments(activity, isMulti , this)
     }

     override fun init() {
         super.init()
         adapter = DocAttachmentsAdapter(context , this)
         dataBinding?.recyclarView?.adapter = adapter
     }

     override fun getUploadBtn(): View? {
        return dataBinding?.uploadBtn
     }


     override val layoutRes: Int
         get() = R.layout.default_doc_attach_field

     override fun onAttachmentsSelected(attachments: List<AttachItemModel?>) {
         adapter.addItems(attachments)
     }

     override fun editAttachmentClick(pos: Int, attachItemModel: AttachItemModel) {

     }

     override fun deleteAttachmentClick(pos: Int, attachItemModel: AttachItemModel) {

         adapter.deleteItem(pos , attachItemModel)
     }

 }

