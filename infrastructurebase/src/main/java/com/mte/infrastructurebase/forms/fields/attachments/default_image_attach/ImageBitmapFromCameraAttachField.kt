package com.mte.infrastructurebase.forms.fields.attachments.default_image_attach

import android.app.Activity
import android.graphics.Bitmap
import android.view.View
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.attachments.ImageBitmapFromCamera
import com.mte.infrastructurebase.attachments.OnTakeBitmapPhotoListener
import com.mte.infrastructurebase.databinding.DefaultImageCameraFieldBinding
import com.mte.infrastructurebase.forms.fields.attachments.*
import com.mte.infrastructurebase.forms.interfaces.IRule
import java.io.File

class ImageBitmapFromCameraAttachField(
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
) , OnAttachAction, OnTakeBitmapPhotoListener {


    init {
        baseAttachments = ImageBitmapFromCamera(activity, isMulti , this)
    }

    override fun getUploadBtn(): View? = dataBinding?.uploadBtn


    override fun editAttachmentClick(pos: Int, attachItemModel: AttachItemModel) {}

    override fun deleteAttachmentClick(pos: Int, attachItemModel: AttachItemModel) {
    }

    override val layoutRes: Int
        get() = R.layout.default_image_camera_field

    override fun OnTakePhoto(image: Bitmap) {
        dataBinding?.imageIV?.setImageBitmap(image)
    }


}