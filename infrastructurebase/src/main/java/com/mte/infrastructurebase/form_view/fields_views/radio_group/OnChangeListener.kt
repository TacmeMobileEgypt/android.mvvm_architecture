package com.mte.infrastructurebase.form_view.fields_views.radio_group

interface OnChangeListener {
    fun getValue(checkedRadioButtonId: Int): String?
    fun getCheckedId(text: String?): Int?
}