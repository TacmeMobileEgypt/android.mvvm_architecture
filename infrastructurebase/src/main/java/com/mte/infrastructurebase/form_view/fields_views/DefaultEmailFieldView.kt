package com.mte.infrastructurebase.form_view.fields_views

import android.content.Context
import android.text.InputType
import android.util.AttributeSet

open class DefaultEmailFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : DefaultTextFieldView(context , attributeSet) {

    override var fieldInputType: Int = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

}