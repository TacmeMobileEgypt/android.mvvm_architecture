package com.hamdy.infrastructurebase.attachments

import android.app.Activity
import android.content.Intent
import android.os.Build
import com.hamdy.infrastructurebase.forms.fields.attachments.AttachItemModel


class ImageAttachments(activity: Activity,
                       isMulti : Boolean,
                       onAttachmentSelectedListener : OnAttachmentSelectedListener? = null
) : BaseAttachments(activity , isMulti , onAttachmentSelectedListener) {

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