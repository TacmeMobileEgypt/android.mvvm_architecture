package com.mte.infrastructurebase.defaults

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.base.base_activity.IWrapEmptyData
import com.mte.infrastructurebase.base.base_activity.OnRetryClick
import com.mte.infrastructurebase.databinding.DefaultEmptyDataLayoutBinding


class DefaultWrapEmptyData(val context: Context, val retryText : String? ="Retry") : IWrapEmptyData {

    private var databinding: DefaultEmptyDataLayoutBinding? = null

    var layoutId : Int = R.layout.default_empty_data_layout


    override fun addEmptyView(root: ViewGroup?, msge: String?, onRetryClick: OnRetryClick) {
        databinding =  DataBindingUtil.bind<DefaultEmptyDataLayoutBinding>(LayoutInflater.from(context).inflate(layoutId, null) )

        root?.removeAllViews()
        databinding?.text = msge
        databinding?.tryAgainText = retryText
        databinding?.iTryClick = onRetryClick

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