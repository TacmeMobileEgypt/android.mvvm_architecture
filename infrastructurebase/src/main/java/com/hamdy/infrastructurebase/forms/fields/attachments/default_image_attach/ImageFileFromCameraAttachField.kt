package com.hamdy.infrastructurebase.forms.fields.attachments.default_image_attach

import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.attachments.ImageBitmapFromCamera
import com.hamdy.infrastructurebase.attachments.ImageFileFromCamera
import com.hamdy.infrastructurebase.attachments.OnTakeBitmapPhotoListener
import com.hamdy.infrastructurebase.attachments.OnTakeFilePhotoListener
import com.hamdy.infrastructurebase.databinding.DefaultImageCameraFieldBinding
import com.hamdy.infrastructurebase.forms.fields.attachments.*
import com.hamdy.infrastructurebase.forms.interfaces.IRule
import java.io.File

class ImageFileFromCameraAttachField(
    activity: Activity,
    title: String,
    tag: String? = null,
     isEditable: Boolean = true,
     singleSelection: Boolean = false,
    rules: List<IRule<File>>? = ArrayList(),
     model: ((File?) -> Unit)? = null,
     isMulti : Boolean = false

) : BaseAttachmentsField<DefaultImageCameraFieldBinding>(
    activity,
    title,
    tag,
    isEditable,
    singleSelection,
    rules,
    model,
    isMulti
) , OnAttachAction, OnTakeFilePhotoListener {


    init {
        baseAttachments = ImageFileFromCamera(activity, isMulti , this)
    }

    override fun getUploadBtn(): View? = dataBinding?.uploadBtn


    override fun editAttachmentClick(pos: Int, attachItemModel: AttachItemModel) {}

    override fun deleteAttachmentClick(pos: Int, attachItemModel: AttachItemModel) {
    }

    override val layoutRes: Int
        get() = R.layout.default_image_camera_field


    override fun OnTakePhoto(image: Uri?) {
        dataBinding?.imageIV?.setImageURI(image)
    }

}