package com.mte.infrastructurebase.form_view.fields_views.attachments

import android.app.Activity
import android.content.Intent
import com.mte.infrastructurebase.attachments.BaseAttachments
import com.mte.infrastructurebase.attachments.OnAttachmentSelectedListener


class DocAttachments(activity: Activity ,
                     isMulti : Boolean = false ,
                     onAttachmentSelectedListener : OnAttachmentSelectedListener? = null) : BaseAttachments(activity , isMulti , onAttachmentSelectedListener) {


    override var RESULT_CODE: Int = 1999

    override fun createIntent(): Intent? {
        val intent = Intent()

        intent.type = "text/*"

        intent.action = Intent.ACTION_GET_CONTENT

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            intent.type = "*/*"
//            intent.action = Intent.ACTION_OPEN_DOCUMENT
//
//            val mimetypes = arrayOf(
//                "application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
//                "application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
//                "application/vnd.ms-excel","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
//                "text/plain",
//                "application/pdf",
//                "application/zip"
//            )
//            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes)
//        }else{
//
//            intent.type = "text/*"
//
//            intent.action = Intent.ACTION_GET_CONTENT
//        }

        intent.addCategory(Intent.CATEGORY_OPENABLE)

        return intent

    }
}