package com.mte.infrastructurebase.form_view.fields_views

import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import android.widget.TimePicker
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IRule
import com.mte.infrastructurebase.utils.KeyboardUtils
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

open class TimePickerFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : TextView(context, attributeSet) , IFieldView,TimePickerDialog.OnTimeSetListener {


     var is24: Boolean = true
    private val timeFormat: String = "HH:mm"
    private lateinit var timeString: String


    var initialCurrentDate : Boolean    = false
    set(value) {
        field = value
        if(field)
            initCurrentDate()

    }


    private lateinit var myCalender: Calendar
    private lateinit var timePickerDialog: TimePickerDialog


    private var attrChange: InverseBindingListener? = null

     var rules :  List<IRule<String>>? = null

    val validationMessages: ArrayList<String>? = ArrayList()

    init {
        Locale.setDefault(Locale.ENGLISH)
        init()
    }

    private fun init() {

        myCalender = Calendar.getInstance(Locale.ENGLISH)

        if(initialCurrentDate)
            initCurrentDate()

        setOnClickListener {
            openTimePickerDialog()
        }
    }

    private fun initCurrentDate() {
        myCalender = Calendar.getInstance(Locale.ENGLISH)

        val hourOfDay = myCalender[Calendar.HOUR_OF_DAY]
        val minute = myCalender[Calendar.MINUTE]

        onTimeSet(null , hourOfDay , minute)
    }

    private fun openTimePickerDialog() {

        KeyboardUtils.hideKeyboardInDialogFragment(this)

        val hourOfDay = myCalender[Calendar.HOUR_OF_DAY]
        val minute = myCalender[Calendar.MINUTE]


        timePickerDialog = TimePickerDialog(
           context,
            this,
            hourOfDay,
            minute,
            is24
        )

        timePickerDialog.show()
    }

    override fun isValid(): Boolean {

        validationMessages?.clear()

        rules?.forEach {
            val message = it.validate(getValue())
            if (message != null)
                validationMessages?.add(message)
        }

        return validationMessages?.size == 0
    }

    override fun getValidationMessage(): String? {
       return validationMessages?.get(0)
    }

    override fun setValue(text: String?) {
        try {

            //Parse value to date
            val sdf  = SimpleDateFormat(timeFormat, Locale.US)
            val date = sdf.parse(text!!)
            myCalender.time = date
            setText(text)
        } catch (ex : Exception) {
                ex.printStackTrace()
        }

    }


    override fun getValue(): String? {
        return text.toString().trim()
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        this.attrChange = attrChange
    }


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myCalender[Calendar.HOUR_OF_DAY] = hourOfDay
        myCalender[Calendar.MINUTE] = minute
        timeString = String.format(Locale.ENGLISH, "%02d:%02d", hourOfDay, minute)
        setValue(timeString)
        attrChange?.onChange()

    }
}