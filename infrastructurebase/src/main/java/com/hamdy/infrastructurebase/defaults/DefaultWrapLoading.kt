package com.hamdy.infrastructurebase.defaults

import android.content.Context
import android.view.*
import androidx.appcompat.app.AlertDialog
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.base.base_activity.IDialogLoading
import com.hamdy.infrastructurebase.base.base_activity.IWrapLoading

class DefaultWrapLoading(val context: Context) : IWrapLoading {


    private var loadingView: View? = null

    var layoutIdLoading : Int = R.layout.layout_loading_dialog_default

    init {
        init()
    }

    private fun init() {
      loadingView =  LayoutInflater.from(context).inflate(layoutIdLoading, null, false)

    }

    override fun addLoadingView(root: ViewGroup?) {
        root?.removeAllViews()
        if(loadingView?.parent != null){
            (loadingView?.parent as ViewGroup).removeView(loadingView)
        }
        root?.addView(loadingView)
    }
}