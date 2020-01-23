package com.mte.infrastructurebase.databinding

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.mte.infrastructurebase.form_view.interfaces.IFieldView
import com.mte.infrastructurebase.forms.fields.attachments.AttachItemModel

object FormBinding {


    private val TAG: String? = "FormBinding"


    //START TEST BINDING
    @SuppressLint("LogNotTimber")
    @JvmStatic
    @BindingAdapter( "textBinding")
    fun setBindText(fieldView: View, text: String?) {
        Log.e(TAG , "setBindText $text")

        if(fieldView is IFieldView<*>)
            (fieldView as IFieldView<String?>).setValue(text)
    }

    @SuppressLint("LogNotTimber")
    @JvmStatic
    @InverseBindingAdapter(attribute = "textBinding")
    fun getBindText(fieldView: View) : String? {

        if(fieldView is IFieldView<*>){
            Log.e(TAG , "getBindText ${fieldView.getValue()}")
            return  (fieldView as IFieldView<String?>).getValue()
        }

        return null
    }

    @SuppressLint("LogNotTimber")
    @BindingAdapter("app:textBindingAttrChanged")
    @JvmStatic fun setListeners(
        fieldView: View,
        attrChange: InverseBindingListener) {

        if(fieldView is IFieldView<*>){
            Log.e(TAG , "setListeners ${fieldView.getValue()}")
            fieldView.setAttrChange(attrChange)
        }

    }
    //END TEST BINDING


    ////START BOOLEAN BINDING
    @SuppressLint("LogNotTimber")
    @JvmStatic
    @BindingAdapter( "boolBinding")
    fun setBindingBool(fieldView: View, b: Boolean?) {
        Log.e(TAG , "boolBinding $b")

        if(fieldView is IFieldView<*>)
            (fieldView as IFieldView<Boolean?>).setValue(b)
    }

    @SuppressLint("LogNotTimber")
    @JvmStatic
    @InverseBindingAdapter(attribute = "boolBinding")
    fun getBindingBool(fieldView: View) : Boolean? {

        if(fieldView is IFieldView<*>){
            Log.e(TAG , "BindingBool ${fieldView.getValue()}")
            return  (fieldView as IFieldView<Boolean?>).getValue()
        }
        return null
    }

    @SuppressLint("LogNotTimber")
    @BindingAdapter("app:boolBindingAttrChanged")
    @JvmStatic fun setBoolBindingListeners(
        fieldView: View,
        attrChange: InverseBindingListener) {

        if(fieldView is IFieldView<*>){
            Log.e(TAG , "setBoolBindingListeners ${fieldView.getValue()}")
            fieldView.setAttrChange(attrChange)
        }
    }
    ////END BOOLEAN BINDING


    ////START Attachment BINDING
    @SuppressLint("LogNotTimber")
    @JvmStatic
    @BindingAdapter( "attachBinding")
    fun setBindingAttach(fieldView: View, attachItemModel: AttachItemModel?) {
        Log.e(TAG , "attachBinding $attachItemModel")

        if(fieldView is IFieldView<*>)
            (fieldView as IFieldView<AttachItemModel?>).setValue(attachItemModel)
    }

    @SuppressLint("LogNotTimber")
    @JvmStatic
    @InverseBindingAdapter(attribute = "attachBinding")
    fun getBindingAttach(fieldView: View) : AttachItemModel? {

        if(fieldView is IFieldView<*>){
            Log.e(TAG , "BindinAttach ${fieldView.getValue()}")
            return  (fieldView as IFieldView<AttachItemModel?>).getValue()
        }
        return null
    }

    @SuppressLint("LogNotTimber")
    @BindingAdapter("app:attachBindingAttrChanged")
    @JvmStatic fun setAttachBindingListeners(
        fieldView: View,
        attrChange: InverseBindingListener) {

        if(fieldView is IFieldView<*>){
            Log.e(TAG , "setAttachBindingListeners ${fieldView.getValue()}")
            fieldView.setAttrChange(attrChange)
        }
    }
    ////END Attachment BINDING
}