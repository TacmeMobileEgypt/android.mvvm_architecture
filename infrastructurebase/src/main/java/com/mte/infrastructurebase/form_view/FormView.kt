package com.mte.infrastructurebase.form_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.databinding.DefaultFormLayoutBinding
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.form_view.interfaces.IValidationView
import com.mte.infrastructurebase.form_view.interfaces.ValidationFieldViewHandler
import com.mte.infrastructurebase.forms.base.BaseField
import com.mte.infrastructurebase.forms.interfaces.IRule
import com.mte.infrastructurebase.forms.interfaces.ValidationHandler
import java.lang.Exception

class FormView(context: Context , attributeSet: AttributeSet? = null) : LinearLayout(context , attributeSet) {


    private val fieldsMap = HashMap<String, IFormControl>()
    private val validationFields: ArrayList<IFormControl?> = ArrayList()
    protected lateinit var dataBinding: DefaultFormLayoutBinding
    private val fields: ArrayList<IFormControl> = ArrayList()

    fun validate(validationHandler: ValidationFieldViewHandler) {
        validationFields.clear()

        fields.forEach {
            if (!it.isValid())
                validationFields.add(it)
        }

        if (validationFields.size == 0)
            validationHandler.onSuccess()
        else
            validationHandler.onFailure(validationFields)
    }

    fun <F : IFormControl> getFieldsByTag(tag: String?): F? {
        return if (fieldsMap.get(tag) != null) fieldsMap.get(tag) as F else null
    }

    /*Add Field*/
    private fun addFieldToForm(field: IFormControl, postion: Int = -1) {

        if (!field.getTag().isNullOrEmpty())
            fieldsMap.put(field.getTag()!!, field)

        if (postion > -1)
            addView(field.getView(), postion)
        else
            addView(field.getView(), postion)

//        field.position = indexOfChild(field) ?: -1
    }

    fun addField(field: IFormControl, postion: Int = -1) {
        fields.add(field)
        addFieldToForm(field)
    }

    /*Delete Field*/
    fun deleteField(tag: String) {
        fieldsMap[tag]?.let {
            (it?.getView()?.parent as ViewGroup).removeView(it.getView())
        }

        (fields as ArrayList).remove(getFieldsByTag<IFormControl>(tag))
        fieldsMap.remove(tag)
    }

    fun replaceField(tag: String, baseField: IFormControl?, pos: Int?) {
        if(baseField == null) return
        deleteField(tag)
        addField(baseField , pos ?: -1)
    }

    override fun addView(child: View?) {
        super.addView(child)
        if(child is  IFormControl){
            addFieldToMap(child)
        }
    }

    private fun addFieldToMap(child: IFormControl) {
        fields.add(child)
        if (!child.getTag().isNullOrEmpty())
            fieldsMap[child.getTag()!!] = child
    }

    override fun addView(child: View?, index: Int) {
        if(child != null)
            super.addView(child, index)

        if(child is  IFormControl)
            addFieldToMap(child)
    }


    override fun onFinishInflate() {
        super.onFinishInflate()

        try {
            addchildernViews(this)
        }catch (ex : Exception){
            ex.printStackTrace()
        }

    }

    private fun addchildernViews(child: View) {

        if(child is IFormControl) {
            addFieldToMap(child)
        }else if(child is ViewGroup){
            for (i in 0 until child.childCount)
                    addchildernViews(child.getChildAt(i))
        }
    }



}