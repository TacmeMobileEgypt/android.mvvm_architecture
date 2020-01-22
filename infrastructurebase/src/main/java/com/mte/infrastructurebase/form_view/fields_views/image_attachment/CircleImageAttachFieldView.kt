package com.mte.infrastructurebase.form_view.fields_views.image_attachment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.attachments.OnAttachmentSelectedListener
import com.mte.infrastructurebase.form_view.fields_views.attachments.ImageAttachment
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.forms.fields.attachments.AttachItemModel
import com.mte.infrastructurebase.forms.interfaces.IRule
import de.hdodenhof.circleimageview.CircleImageView

open class CircleImageAttachFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : CircleImageView(context, attributeSet), IFieldView<AttachItemModel?> {


    private var formControl: IFormControl? = null
    private var selectedImage: AttachItemModel? = null

     var attachment: List<AttachItemModel?>? = null

    val baseAttachments = ImageAttachment()

    private var attrChange: InverseBindingListener? = null

    var rules: List<IRule<AttachItemModel>>? = null

    val validationMessages: ArrayList<String>? = ArrayList()

    init {
        init()
    }

    private fun init() {

        baseAttachments.onAttachmentSelectedListener =
            object : OnAttachmentSelectedListener {
                override fun onAttachmentsSelected(attachments: List<AttachItemModel?>) {
                    this@CircleImageAttachFieldView.attachment = attachments
                    selectedImage = attachment?.get(0)
                    attrChange?.onChange()
                    setValue(selectedImage)
                }
            }

        setOnClickListener {
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

            if(attachItemModel?.fileUri != null)
                setImageURI(attachItemModel?.fileUri)

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
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        baseAttachments.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun setFormControl(formControl: IFormControl?) {
        this.formControl = formControl
    }
}