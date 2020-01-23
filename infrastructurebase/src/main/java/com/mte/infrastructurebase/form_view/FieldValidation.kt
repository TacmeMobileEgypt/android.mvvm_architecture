package com.mte.infrastructurebase.form_view


import android.view.View
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.form_view.interfaces.IFormControl
import com.mte.infrastructurebase.form_view.interfaces.IValidationView
import com.mte.infrastructurebase.forms.interfaces.IRule

class FieldValidation<T>(var value : T? ,
                      val validationTag : String? = null,
                      val rules :  List<IRule<T>>? = null ,
                      showValidtionMsg : (msg : String?) -> Unit) :  IFormControl {


    private var validationView: IValidationView? = object : IValidationView{

        var msg : String ? = null

        override fun resetValidationView() {
            msg = null
        }

        override fun showValidateError() {
            showValidtionMsg.invoke(getValidationMessage())
        }

        override fun getValidationMessage(): String? {
            return msg
        }

        override fun setMessage(validationMessage: String?) {
            this.msg = validationMessage
        }

    }

    private var fieldView: IFieldView<T>? = object : IFieldView<T>{

        val validationMessages: ArrayList<String>? = ArrayList()


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

        override fun setValue(text: T?) {}

        override fun getValue(): T? {
            return value
        }

        override fun setAttrChange(attrChange: InverseBindingListener) {}

        override fun setFormControl(formControl: IFormControl?) { }

    }


     override fun isValid(): Boolean {
         validationView?.resetValidationView()

         if(fieldView?.isValid() == true)
             return true

         validationView?.setMessage(fieldView?.getValidationMessage())

        return false
     }

     override fun getTag(): String? {
         return  validationTag
     }

     override fun getView(): View? {
         return null
     }

     override fun getValidationView(): IValidationView? {
         return validationView
     }

    override fun getFiledView(): IFieldView<*>? {
        return fieldView
    }

}