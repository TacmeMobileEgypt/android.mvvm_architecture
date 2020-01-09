package com.hamdy.infrastructurebase.forms.fields.spinner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.forms.models.OptionModel

class SpinnerAdapter(
    context: Context,
    @LayoutRes val layoutResId: Int = R.layout.item_spinner_layout,
    var items: ArrayList<OptionModel>? = ArrayList(),
    @IdRes val textViewResourceId: Int = R.id.itemSpinnerTV,
    val  prompt: String? = null
) : ArrayAdapter<String>(context , layoutResId , textViewResourceId){

    init {
        addPromptOption()

    }

    private fun addPromptOption() {
        if(prompt == null) return

        items?.add(0 , OptionModel(null , prompt))
    }


    override fun getCount(): Int {
        return items?.size?: 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = LayoutInflater.from(context).inflate(layoutResId , null )
        view.findViewById<TextView>(textViewResourceId).text = items?.get(position)?.text
        return view
    }

    fun updateItems(items: ArrayList<OptionModel>?) {
        this.items = items
        addPromptOption()
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): String? {
        return items?.get(position)?.text
    }

}