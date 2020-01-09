package com.hamdy.infrastructurebase.forms.fields

import android.text.InputType
import com.hamdy.infrastructurebase.forms.interfaces.IRule

class DefaultNumberField(
    title : String,
    tag: String? = null,
    rules: List<IRule<String>>? = ArrayList(),
    model: ((String?) -> Unit)? = null,
    hint: String? = null,
    lines: Int = 1,
    inputType: Int = InputType.TYPE_CLASS_NUMBER
) : DefaultTextField(title , tag , rules , model , hint , lines , inputType)