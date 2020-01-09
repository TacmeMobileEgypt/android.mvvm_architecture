package com.hamdy.infrastructurebase.defaults

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.base.base_activity.IWrapEmptyData
import com.hamdy.infrastructurebase.base.base_activity.OnRetryClick
import com.hamdy.infrastructurebase.databinding.EmptyDataLayoutBinding


class DefaultWrapEmptyData(val context: Context, val retryText : String? ="Retry") : IWrapEmptyData {

    private var databinding: EmptyDataLayoutBinding? = null

    var layoutId : Int = R.layout.empty_data_layout

    init {
        init()
    }

    private fun init() {
         databinding =  DataBindingUtil.bind<EmptyDataLayoutBinding>(LayoutInflater.from(context).inflate(layoutId, null) )
    }

    override fun addEmptyView(root: ViewGroup?, msge: String?, onRetryClick: OnRetryClick) {

        root?.removeAllViews()
        databinding?.text = msge
        databinding?.tryAgainText = retryText
        databinding?.iTryClick = onRetryClick

        if (databinding?.root?.parent != null) {
            (databinding?.root?.parent as ViewGroup).removeView(databinding?.root) // <- fix
        }

        root?.addView(databinding?.root)
    }

    override fun addEmptyView(root: ViewGroup?, onRetryClick: OnRetryClick) {
        root?.removeAllViews()
        databinding?.text = "No Data Found"
        databinding?.tryAgainText = retryText
        databinding?.iTryClick = onRetryClick
        if(databinding?.root?.parent != null){
            (databinding?.root?.parent as ViewGroup).removeView(databinding?.root)
        }
        root?.addView(databinding?.root)
    }
}