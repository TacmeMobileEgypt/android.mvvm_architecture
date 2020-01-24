package com.mte.baseinfrastructure

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.databinding.ViewDataBinding
 import com.mte.baseinfrastructure.databinding.ActivityMainBinding
import com.mte.infrastructurebase.base.base_activity.BaseActivity
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.form_view.interfaces.ValidationFieldViewHandler
import com.mte.infrastructurebase.form_view.validationRules.Required
import com.mte.infrastructurebase.interfaces.OnLocationSuccessListener
import com.mte.infrastructurebase.utils.FusedLocation

class MainActivity : BaseActivity<ActivityMainBinding>() {


    private var fusedLocation: FusedLocation? = null

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {

        initForm()

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

        getLocation()


    }

    private fun getLocation() {

        fusedLocation = FusedLocation(this)

        fusedLocation?.getLocation(object  : OnLocationSuccessListener {
            override fun onLocationSuccess(location: Location?) {
                Log.e("llllllllll" , "location ${location.toString()}")
            }
        })
    }

    private fun initForm() {
        binding.fileAttach.activity = this
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        fusedLocation?.onRequestPermissionsResult(requestCode, permissions, grantResults)
        binding.fileAttach.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.fileAttach.onActivityResult(requestCode, resultCode, data)
    }
}
