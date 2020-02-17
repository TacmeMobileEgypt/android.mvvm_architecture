package com.mte.infrastructurebase.defaults

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.base.base_activity.IWrapEmptyData
import com.mte.infrastructurebase.base.base_activity.OnRetryClick
import com.mte.infrastructurebase.databinding.DefaultEmptyDataLayoutBinding


class DefaultWrapEmptyData(val context: Context) : IWrapEmptyData {

    private var databinding: DefaultEmptyDataLayoutBinding? = null

    var layoutId : Int = R.layout.default_empty_data_layout

    override fun addEmptyView(root: ViewGroup?, msge: String?) {
        databinding =  DataBindingUtil.bind<DefaultEmptyDataLayoutBinding>(LayoutInflater.from(context).inflate(layoutId, null) )
        root?.removeAllViews()
        databinding?.text = msge
        root?.addView(databinding?.root)
    }

    override fun addEmptyView(root: ViewGroup?) {
        databinding =  DataBindingUtil.bind<DefaultEmptyDataLayoutBinding>(LayoutInflater.from(context).inflate(layoutId, null) )
        root?.removeAllViews()
        databinding?.text = "No Data Found"
        root?.addView(databinding?.root)
    }
}