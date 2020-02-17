package com.mte.infrastructurebase.defaults

import android.content.Context
import android.view.*
import androidx.databinding.DataBindingUtil
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.base.base_activity.IWrapError
import com.mte.infrastructurebase.base.base_activity.OnRetryClick
import com.mte.infrastructurebase.databinding.DefaultErrorLayoutBinding

class DefaultWrapError(val context: Context , val retryText : String? ="Retry") : IWrapError {

    private var databinding: DefaultErrorLayoutBinding? = null

    var layoutId : Int = R.layout.default_error_layout

    override fun addErrorView(root: ViewGroup?, msge: String?, onRetryClick: OnRetryClick) {
        databinding =  DataBindingUtil.bind<DefaultErrorLayoutBinding>(LayoutInflater.from(context).inflate(layoutId , null) )
        root?.removeAllViews()
        databinding?.text = msge ?: "An error occurred"
        databinding?.tryAgainText = retryText
        databinding?.iTryClick = onRetryClick
        root?.addView(databinding?.root)
    }
}