package com.mte.infrastructurebase.forms.fields.spinner

import android.view.View
import android.widget.AdapterView
import com.mte.infrastructurebase.forms.DefaultLayoutField
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.databinding.SpinnerLayoutBinding
import com.mte.infrastructurebase.forms.interfaces.IRule
import com.mte.infrastructurebase.forms.models.OptionModel


open class DefaultSpinnerField(
    title : String? = null,
    tag: String? = null,
    rules: List<IRule<String>>? = ArrayList(),
    model: ((String?) -> Unit)? = null,
    prompt: String? = null,
    items : ArrayList<OptionModel>
) : DefaultLayoutField<SpinnerLayoutBinding, String>(title , tag , rules) {


    private var dataAdapter: SpinnerAdapter? = null

    var model: ((String?) -> Unit)? = model
        set(value) {
            field = value
        }

    override var value: String? = null
        set(value) {
            field = value
            model?.invoke(value)
        }

    var prompt: String? = prompt
        set(value) {
            field = value
            dataBinding?.prompt = prompt
        }

    var items:ArrayList<OptionModel>? = items
        set(value) {
            field = value
            dataAdapter?.updateItems(field)
        }


    override fun init() {
        super.init()

        dataBinding?.prompt = prompt?: title

        dataAdapter = SpinnerAdapter(
            context = context!!,
            items =  items,
            prompt = null
        )
        dataBinding?.spinner?.setAdapter(dataAdapter)

        dataBinding?.spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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

    override val layoutRes: Int
        get() = R.layout.spinner_layout




}