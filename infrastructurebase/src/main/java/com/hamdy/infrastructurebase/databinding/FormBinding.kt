package com.hamdy.infrastructurebase.databinding

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.hamdy.infrastructurebase.form_view.interfaces.IFieldView

object FormBinding {


    private val TAG: String? = "FormBinding"

    @SuppressLint("LogNotTimber")
    @JvmStatic
    @BindingAdapter( "textBinding")
    fun setBindText(fieldView: View, text: String?) {
        Log.e(TAG , "setBindText $text")

        if(fieldView is IFieldView)
            fieldView.setValue(text)
    }

    @SuppressLint("LogNotTimber")
    @JvmStatic
    @InverseBindingAdapter(attribute = "textBinding")
    fun getBindText(fieldView: View) : String? {

        if(fieldView is IFieldView){
            Log.e(TAG , "getBindText ${fieldView.getValue()}")
            return fieldView.getValue()
        }

        return null
    }

    @SuppressLint("LogNotTimber")
    @BindingAdapter("app:textBindingAttrChanged")
    @JvmStatic fun setListeners(
        fieldView: View,
        attrChange: InverseBindingListener
    ) {

        if(fieldView is IFieldView){
            Log.e(TAG , "setListeners ${fieldView.getValue()}")
            fieldView.setAttrChange(attrChange)
        }

    }


//    @SuppressLint("LogNotTimber")
//    @JvmStatic
//    @BindingAdapter("app:textBinding")
//    fun setItemsBind( fieldView: View , text: String?) {
//
//        if(fieldView is IFieldView){
//            Log.e(TAG , "setBindText $text")
//            fieldView.setValue(text)
//        }
//
//    }
}