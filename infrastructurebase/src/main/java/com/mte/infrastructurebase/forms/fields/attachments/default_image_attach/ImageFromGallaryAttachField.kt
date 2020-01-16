package com.mte.infrastructurebase.forms.fields.attachments.default_image_attach

import android.app.Activity
import android.view.View
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.attachments.ImageAttachments
import com.mte.infrastructurebase.attachments.OnAttachmentSelectedListener
import com.mte.infrastructurebase.databinding.DefaultImageAttachFieldBinding
import com.mte.infrastructurebase.forms.fields.attachments.*
import com.mte.infrastructurebase.forms.interfaces.IRule
import java.io.File

class ImageFromGallaryAttachField(
    activity: Activity,
    title: String,
    tag: String? = null,
     isEditable: Boolean = true,
     singleSelection: Boolean = false,
    rules: List<IRule<File>>? = ArrayList(),
     model: ((File?) -> Unit)? = null,
     isMulti : Boolean = false

) : BaseAttachmentsField<DefaultImageAttachFieldBinding>(
    activity,
    title,
    tag,
    isEditable,
    singleSelection,
    rules,
    model,
    isMulti
) , OnAttachAction, OnAttachmentSelectedListener {

    private lateinit var adapter: AttachmentListAdapter

    init {
        baseAttachments = ImageAttachments(activity, isMulti , this)
    }

    override fun getUploadBtn(): View? = dataBinding?.uploadBtn

    override fun init() {
        super.init()
        adapter = AttachmentListAdapter(context , this)
        dataBinding?.recyclarView?.adapter = adapter
    }

    override fun editAttachmentClick(pos: Int, attachItemModel: AttachItemModel) {}

    override fun deleteAttachmentClick(pos: Int, attachItemModel: AttachItemModel) {
        adapter.deleteItem(pos , attachItemModel)
    }

    override val layoutRes: Int
        get() = R.layout.default_image_attach_field

    override fun onAttachmentsSelected(attachments: List<AttachItemModel?>) {
        adapter.addItems(attachments)

    }

}