package com.hamdy.infrastructurebase.forms.fields.spinner

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.databinding.SpinnerLayoutBinding
import com.hamdy.infrastructurebase.form_view.DefaultLayoutFieldView
import com.hamdy.infrastructurebase.forms.models.OptionModel


 class DefaultSpinnerFieldView(
    context: Context,
    attributeSet: AttributeSet? = null
) : Spinner(context, attributeSet) {


    private var dataAdapter: SpinnerAdapter? = null

    var model: ((String?) -> Unit)? = null
        set(value) {
            field = value
        }

     var value: String? = null
        set(value) {
            field = value
            model?.invoke(value)
        }


    var items:ArrayList<OptionModel>? = null
        set(value) {
            field = value
            dataAdapter?.updateItems(field)
        }


     fun init() {


        dataAdapter = SpinnerAdapter(
            context = context!!,
            items =  items,
            prompt = null
        )
       setAdapter(dataAdapter)

       onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                value = items?.get(position)?.id
            }

        }
    }


     fun onValueChanged(value: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}