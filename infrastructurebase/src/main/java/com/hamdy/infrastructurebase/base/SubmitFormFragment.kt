package com.hamdy.infrastructurebase.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.hamdy.infrastructurebase.forms.Form
import com.hamdy.infrastructurebase.forms.base.BaseField
import com.hamdy.infrastructurebase.forms.interfaces.ValidationHandler
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.databinding.SubmitFormFragmentBinding
import com.hamdy.infrastructurebase.forms.fields.attachments.BaseAttachmentsField

abstract class SubmitFormFragment : BaseFragment<SubmitFormFragmentBinding>() {

    protected  var form: Form? = null

    override val layoutRes: Int
        get() = R.layout.submit_form_fragment

    protected var submitBtnText: String? = null
        set(value) {
        field = value
        binding.submitBtn.text = value
    }

    protected open var attachTag : String?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attachTag = getString(R.string.attachments)
    }


    open  fun getSubmitBtn() : View? = binding.submitBtn


    override fun initUI(savedInstanceState: Bundle?) {

       createForm()

        getSubmitBtn()?.setOnClickListener {

            form?.validate(object : ValidationHandler {
                override fun onSuccess() {
                    onValidationSuccess()
                }
                override fun onFailure(fields: ArrayList<BaseField<*, *>>) {
                    onValidationFailed(fields)
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(attachTag != null) {
            form?.getFieldsByTag<BaseField<*, *>>(attachTag!!)?.apply {
                if (this is BaseAttachmentsField<*>) {
                    this.onActivityResult(requestCode, resultCode, data)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(tag != null) {
            form?.getFieldsByTag<BaseField<*, *>>(attachTag)?.apply {
                if (this is BaseAttachmentsField<*>) {
                    this.onRequestPermissionsResult(
                        requestCode,
                        permissions as Array<String>,
                        grantResults
                    )
                }
            }
        }
    }


    open fun onValidationFailed(fields: java.util.ArrayList<BaseField<*, *>>){

        fields.forEach {
            if(it.validationMessages?.size?: 0 > 0)
                showInfoMsgDialog(it.validationMessages?.get(0))
        }
    }

    abstract fun onValidationSuccess()

    abstract fun createForm()

}