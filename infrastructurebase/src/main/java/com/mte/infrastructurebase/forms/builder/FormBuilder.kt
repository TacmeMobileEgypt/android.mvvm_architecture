package com.mte.infrastructurebase.forms.builder

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.mte.infrastructurebase.forms.Form
import com.mte.infrastructurebase.forms.base.BaseField

class FormBuilder(val context: Context, val container: ViewGroup) {

    private var savedInstance: Bundle? = null

    private val fields : ArrayList<BaseField<* , *>> = ArrayList()

    fun addField(field : BaseField<* , *>) : FormBuilder {

        fields.add(field)
        return this
    }

    fun savedInstance(savedInstance: Bundle?): FormBuilder {
        return this.apply {
            this.savedInstance = savedInstance
        }
    }


    fun build() : Form {

        return Form(
            context,
            container,
            savedInstance,
            fields
        ).apply {
            build()
        }
    }


    companion object{

        fun builder(context: Context , container: ViewGroup): FormBuilder {
            return FormBuilder(context, container)
        }

    }

}