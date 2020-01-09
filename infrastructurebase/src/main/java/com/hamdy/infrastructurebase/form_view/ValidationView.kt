package com.hamdy.infrastructurebase.form_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hamdy.infrastructurebase.form_view.interfaces.IValidationView

open class ValidationView(
    context: Context,
    attributeSet: AttributeSet? = null
) : TextView(context, attributeSet) , IValidationView {

    var msg : String ? = null

    init {
        init()
    }

    private fun init() {
        resetValidationView()
        setTextColor(ContextCompat.getColor(context , android.R.color.holo_red_dark))
    }

    override fun resetValidationView() {
        setText(null)
        visibility = View.GONE
    }

    override fun showValidateError() {
        visibility = View.VISIBLE
        setText(msg)
    }

    override fun getValidationMessage(): String? {
        return msg
    }

    override fun setMessage(validationMessage: String?) {
        msg = validationMessage
    }


}