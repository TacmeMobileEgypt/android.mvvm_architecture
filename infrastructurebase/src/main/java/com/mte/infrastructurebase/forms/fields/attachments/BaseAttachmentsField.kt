package com.mte.infrastructurebase.forms.fields.attachments

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.databinding.ViewDataBinding
import com.mte.infrastructurebase.attachments.BaseAttachments
import com.mte.infrastructurebase.forms.DefaultLayoutField
import com.mte.infrastructurebase.forms.interfaces.IRule
import java.io.File



abstract class BaseAttachmentsField<D : ViewDataBinding>(
    val activity: Activity,
    title: String,
    tag: String? = null,
    var isEditable: Boolean = true,
    var isSingleFile: Boolean = false,
    rules: List<IRule<File>>? = ArrayList(),
    var model: ((File?) -> Unit)? = null,
    val isMulti: Boolean = false,
    var baseAttachments: BaseAttachments? = null

) : DefaultLayoutField<D, File>(title, tag, rules) {

    val attachments = ArrayList<AttachItemModel>()

    override fun init() {
        super.init()
        getUploadBtn()?.setOnClickListener(View.OnClickListener {
            if (isEditable && (!isSingleFile || (isSingleFile && attachments.size == 0)))
                onSelectBtnClick(it)
        })
    }

    abstract fun getUploadBtn(): View?

    override fun resetMessageError() {}

    override fun showValidateError() {}

    fun onSelectBtnClick(view: View) {
        baseAttachments?.openSAF()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        baseAttachments?.onActivityResult(requestCode , resultCode , data )
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        baseAttachments?.onRequestPermissionsResult(requestCode , permissions , grantResults)
    }

}

