package com.hamdy.infrastructurebase.form_view.fields_views

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.databinding.EditTextViewLayoutBinding
import com.hamdy.infrastructurebase.form_view.DefaultLayoutFieldView

open class DefaultTextFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : EditText(context, attributeSet) {


    var model: ((String?) -> Unit)? = null
        set(value) {
            field = value
        }


     open var fieldLines: Int = 1

    open var fieldInputType: Int = InputType.TYPE_CLASS_TEXT

    init {
        init()
    }


    open fun init() {
        setInputType(inputType)
        val fArray = arrayOfNulls<InputFilter>(1)
        fArray[0] = InputFilter.LengthFilter(android.R.attr.maxLength)
        setFilters(fArray)

        maxLines = fieldLines
        setLines(fieldLines)

    }




}