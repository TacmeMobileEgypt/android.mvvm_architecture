package com.mte.infrastructurebase.form_view.fields_views

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.widget.DatePicker
import android.widget.TextView
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.forms.interfaces.IRule
import com.mte.infrastructurebase.utils.KeyboardUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


open class DatePickerFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : TextView(context, attributeSet) , IFieldView, DatePickerDialog.OnDateSetListener {


    private var dateTimestamp: Date?            = null

    private var dateFormat : String             = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    private var displayDateFormat : String      = "yyyy-MM-dd"

    private var dateText: String?      = null
    private var displayDatText : String? = null

    var initialCurrentDate : Boolean    = false
    set(value) {
        field = value
        if(field)
            initCurrentDate()

    }


    private lateinit var myCalender: Calendar
    private lateinit var datePickerDialog: DatePickerDialog

    private var attrChange: InverseBindingListener? = null

     var rules :  List<IRule<String>>? = null

    val validationMessages: ArrayList<String>? = ArrayList()

    init {

        init()
    }

    private fun init() {

        myCalender = Calendar.getInstance()

        if(initialCurrentDate)
            initCurrentDate()

        setOnClickListener {
            openDatePickerDialog()
        }
    }

    private fun initCurrentDate() {
        myCalender = Calendar.getInstance()
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val  month = myCalender.get(Calendar.MONTH)
        val year = myCalender.get(Calendar.YEAR)
        onDateSet(null , year , month , day)
    }

    private fun openDatePickerDialog() {

        Locale.setDefault(Locale.ENGLISH)

        KeyboardUtils.hideKeyboardInDialogFragment(this)

        val day = myCalender.get(Calendar.DAY_OF_MONTH)
       val  month = myCalender.get(Calendar.MONTH)
        val year = myCalender.get(Calendar.YEAR)

        datePickerDialog = DatePickerDialog(
            context,
            this,
            year,
            month,
            day
        )

        datePickerDialog.show()
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
            val sdf  = SimpleDateFormat(displayDateFormat, Locale.US)
            dateTimestamp = sdf.parse(text!!)
            myCalender.time = dateTimestamp!!

            dateText = getDateText()

            setText(text)
        } catch (ex : Exception) {
                ex.printStackTrace()
        }

    }

    private fun getDateText(): String? {
        if(dateTimestamp != null) {
            //dateFormat
            var sdf = SimpleDateFormat(dateFormat, Locale.US)
            return  sdf.format(dateTimestamp!!)
        }

        return null
    }

    private fun getDisplayDateText(): String? {
        if(dateTimestamp != null) {
            //dateFormat
            val sdf = SimpleDateFormat(displayDateFormat, Locale(Locale.getDefault().language))
            return  sdf.format(dateTimestamp!!)
        }

        return null
    }

    override fun getValue(): String? {
        return text.toString().trim()
    }

    override fun setAttrChange(attrChange: InverseBindingListener) {
        this.attrChange = attrChange
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        myCalender.set(year, month, dayOfMonth)

        dateTimestamp =  myCalender.time

        dateText = getDateText()

        displayDatText = getDisplayDateText()

        setValue(displayDatText)
        attrChange?.onChange()

    }
}