package com.mte.infrastructurebase.form_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.form_view.interfaces.ILabelView
import com.mte.infrastructurebase.form_view.interfaces.IValidationView
import java.lang.Exception

class FormControlView(
    context: Context,
    attributeSet: AttributeSet? = null) : LinearLayout(context , attributeSet) , IFormControl {


    private var validationView: IValidationView? = null
    private var fieldView: IFieldView<*>? = null
    private var labelView: ILabelView? = null

    override fun onFinishInflate() {
        super.onFinishInflate()

        try {
            addchildernViews(this)
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }

    private fun addchildernViews(child: View) {

        if(child is ViewGroup && !( (child is ILabelView)  || (child is  IFieldView<*>) || (child is IValidationView) )) {
            for (i in 0 until child.childCount) {
                addchildernViews(child.getChildAt(i))
            }
        }else {

            if(child is ILabelView)
                labelView = child

            if(child is IValidationView)
                validationView = child

            if(child is IFieldView<*>)
                fieldView = child


            fieldView?.setFormControl(this)
        }
    }


    override fun isValid(): Boolean {
         validationView?.resetValidationView()

         if(fieldView?.isValid() == true)
             return true

         validationView?.setMessage(fieldView?.getValidationMessage())

        return false
     }

     override fun getTag(): String? {
         return  ""
     }

     override fun getView(): View? {
         return this
     }

     override fun getValidationView(): IValidationView? {
         return validationView
     }

     override fun getFiledView(): IFieldView<*>? {
        return fieldView
     }
 }