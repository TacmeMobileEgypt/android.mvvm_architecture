package com.mte.infrastructurebase.attachments

import android.app.Activity
import android.content.Intent


class ImageAttachments(activity: Activity,
                       isMulti : Boolean,
                       onAttachmentSelectedListener : OnAttachmentSelectedListener? = null
) : BaseAttachments(activity , isMulti , onAttachmentSelectedListener) {

    override var RESULT_CODE: Int = 2999


    override fun createIntent(): Intent? {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"

        return intent

    }
}