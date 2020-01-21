package com.mte.baseinfrastructure

import android.os.Bundle
import android.widget.EditText
import androidx.databinding.ViewDataBinding
 import com.mte.baseinfrastructure.databinding.ActivityMainBinding
import com.mte.infrastructurebase.base.base_activity.BaseActivity
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.form_view.interfaces.ValidationFieldViewHandler
import com.mte.infrastructurebase.form_view.validationRules.Required

class MainActivity : BaseActivity<ActivityMainBinding>() {


    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {

        binding.organizationNameET.rules = listOf(Required("asdfasdf"))

        binding.submitBtn.setOnClickListener {
            binding.form.validate(object : ValidationFieldViewHandler{
                override fun onSuccess() {
//                TODO("Not yet implemented")
                }

                override fun onFailure(fields: ArrayList<IFormControl?>) {
                    fields?.forEach {
                        it?.getValidationView()?.showValidateError()
                        val field = it?.getFiledView()
                        if(field is EditText)
                            field.requestFocus()
                    }
                }

            })
        }



    }
}
