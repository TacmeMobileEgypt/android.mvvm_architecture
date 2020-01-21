package com.mte.baseinfrastructure

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mte.infrastructurebase.form_view.interfaces.ILabelView
import com.mte.infrastructurebase.form_view.interfaces.IValidationView

open class MyLabelView(
    context: Context,
    attributeSet: AttributeSet? = null
) : TextView(context, attributeSet) , IValidationView , ILabelView {

    var msg : String ? = null

    init {
        init()
    }

    private fun init() {
        resetValidationView()
    }

    override fun resetValidationView() {
        setTextColor(ContextCompat.getColor(context , android.R.color.black))
    }

    override fun showValidateError() {
        setTextColor(ContextCompat.getColor(context , android.R.color.holo_red_dark))
    }

    override fun getValidationMessage(): String? {
        return msg
    }

    override fun setMessage(validationMessage: String?) {
        msg = validationMessage
    }


}