package com.hamdy.infrastructurebase.forms.fields

import android.text.InputFilter
import android.text.InputType
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.databinding.EditTextLayoutBinding
import com.hamdy.infrastructurebase.forms.DefaultLayoutField
import com.hamdy.infrastructurebase.forms.interfaces.IRule

open class DefaultTextField(
    title : String,
    tag: String? = null,
    rules: List<IRule<String>>? = ArrayList(),
    model: ((String?) -> Unit)? = null,
    hint: String? = null,
    val lines: Int = 1,
    val inputType: Int = InputType.TYPE_CLASS_TEXT
) : DefaultLayoutField<EditTextLayoutBinding, String>(title , tag , rules) {


    var model: ((String?) -> Unit)? = model
        set(value) {
            field = value
        }

    override var value: String? = null
        set(value) {
            field = value
            model?.invoke(value)
        }

    var hint: String? = hint
        set(value) {
            field = value
            dataBinding?.hint = hint
        }


    override fun init() {
        super.init()

        dataBinding?.hint = hint?: title
        dataBinding?.field = this

        dataBinding?.editText?.setInputType(inputType
        )
        val fArray = arrayOfNulls<InputFilter>(1)
        fArray[0] = InputFilter.LengthFilter(android.R.attr.maxLength)
        dataBinding?.editText?.setFilters(fArray)

        dataBinding?.editText?.maxLines = lines
        dataBinding?.editText?.setLines(lines)

        dataBinding?.field = this
    }

    private val TAG: String = "DefaultTextField"

    override val layoutRes: Int
        get() = R.layout.edit_text_layout




}