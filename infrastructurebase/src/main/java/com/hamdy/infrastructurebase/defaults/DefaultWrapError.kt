package com.hamdy.infrastructurebase.defaults

import android.content.Context
import android.view.*
import androidx.databinding.DataBindingUtil
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.base.base_activity.IWrapError
import com.hamdy.infrastructurebase.base.base_activity.OnRetryClick
import com.hamdy.infrastructurebase.databinding.ErrorLayoutBinding

class DefaultWrapError(val context: Context , val retryText : String? ="Retry") : IWrapError {

    private var databinding: ErrorLayoutBinding? = null

    var layoutId : Int = R.layout.error_layout

    init {
        init()
    }

    private fun init() {
         databinding =  DataBindingUtil.bind<ErrorLayoutBinding>(LayoutInflater.from(context).inflate(layoutId , null) )
    }


    override fun addErrorView(root: ViewGroup?, msge: String?, onRetryClick: OnRetryClick) {
        root?.removeAllViews()
        databinding?.text = msge ?: "An error occurred"
        databinding?.tryAgainText = retryText
        databinding?.iTryClick = onRetryClick
        if(databinding?.root?.parent != null){
            (databinding?.root?.parent as ViewGroup).removeView(databinding?.root)
        }
        root?.addView(databinding?.root)
    }
}