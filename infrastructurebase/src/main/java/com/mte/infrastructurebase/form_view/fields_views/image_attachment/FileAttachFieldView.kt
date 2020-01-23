package com.mte.infrastructurebase.form_view.fields_views.image_attachment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.attachments.OnAttachmentSelectedListener
import com.mte.infrastructurebase.databinding.FileAttachLayoutBinding
import com.mte.infrastructurebase.form_view.fields_views.attachments.FileAttachment
import com.mte.infrastructurebase.form_view.fields_views.attachments.ImageAttachment
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.forms.fields.attachments.AttachItemModel
import com.mte.infrastructurebase.forms.interfaces.IRule
import de.hdodenhof.circleimageview.CircleImageView

open class FileAttachFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : FrameLayout(context, attributeSet), IFieldView<AttachItemModel?> {


    private var binding: FileAttachLayoutBinding? = null
    private var formControl: IFormControl? = null
    private var selectedImage: AttachItemModel? = null

     var attachment: List<AttachItemModel?>? = null

    val baseAttachments = FileAttachment()

    private var attrChange: InverseBindingListener? = null

    var rules: List<IRule<AttachItemModel>>? = null

    val validationMessages: ArrayList<String>? = ArrayList()

    init {
        init()
    }

    private fun init() {

        binding =DataBindingUtil.inflate<FileAttachLayoutBinding>(
            LayoutInflater.from(context),
            R.layout.file_attach_layout,
            this,
            true
        )


        baseAttachments.onAttachmentSelectedListener =
            object : OnAttachmentSelectedListener {
                override fun onAttachmentsSelected(attachments: List<AttachItemModel?>) {
                    this@FileAttachFieldView.attachment = attachments
                    selectedImage = attachment?.get(0)
                    attrChange?.onChange()
                    setValue(selectedImage)
                }
            }

        binding?.root?.setOnClickListener {
            baseAttachments.openSAF()
        }
    }


    override fun isValid(): Boolean {

        validationMessages?.clear()

        rules?.forEach {
            val message = it.validate(getValue())
            if (message != null)
                validationMessages?.add(message)
        }

        return validationMessages?.size == 0
    }

    override fun getValidationMessage(): String? {
        return validationMessages?.get(0)
    }

    override fun setValue(attachItemModel : AttachItemModel?) {
        try {
            formControl?.isValid()

            if(attachItemModel != null)
                binding?.filename = attachItemModel.file?.name

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun getValue(): AttachItemModel? {
        return selectedImage
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        this.attrChange = attrChange
    }

    var activity: Activity? = null
        set(value) {
            baseAttachments.activity = value
            field = value
        }

    var isMulti = false
        set(value) {
            field = value
            baseAttachments.isMulti = isMulti
        }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        baseAttachments.onActivityResult(requestCode, resultCode, data)
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        baseAttachments.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun setFormControl(formControl: IFormControl?) {
        this.formControl = formControl
    }
}