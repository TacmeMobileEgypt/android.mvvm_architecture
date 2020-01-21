package com.mte.infrastructurebase.form_view.fields_views.attachments

import android.app.Activity
import android.content.Intent
import com.mte.infrastructurebase.attachments.OnAttachmentSelectedListener


class FileAttachment(activity: Activity?= null,
                     isMulti : Boolean = false,
                     onAttachmentSelectedListener : OnAttachmentSelectedListener? = null
) : BaseAttachment(activity , isMulti , onAttachmentSelectedListener) {

    override val RESULT_CODE: Int
        get() = 2999

    override fun createIntent(): Intent? {
        val intent = Intent()
        intent.type = "*/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        return intent

    }
}