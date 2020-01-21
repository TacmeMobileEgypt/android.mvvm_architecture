package com.mte.infrastructurebase.form_view.fields_views.attachments

import android.app.Activity
import android.content.Intent
import android.os.Build
import com.mte.infrastructurebase.attachments.OnAttachmentSelectedListener
import com.mte.infrastructurebase.forms.fields.attachments.AttachItemModel


class ImageAttachmentsView(activity: Activity?= null,
                           isMulti : Boolean = false,
                           onAttachmentSelectedListener : OnAttachmentSelectedListener? = null
) : BaseAttachmentsView(activity , isMulti , onAttachmentSelectedListener) {

    override val RESULT_CODE: Int
        get() = 2999

    override fun createIntent(): Intent? {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"

        return intent

    }
}