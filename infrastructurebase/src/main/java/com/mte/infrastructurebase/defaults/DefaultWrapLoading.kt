package com.mte.infrastructurebase.defaults

import android.content.Context
import android.view.*
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.base.base_activity.IWrapLoading

class DefaultWrapLoading(val context: Context) : IWrapLoading {


    private var loadingView: View? = null

    var layoutIdLoading : Int = R.layout.layout_loading_dialog_default

    override fun addLoadingView(root: ViewGroup?) {
        loadingView =  LayoutInflater.from(context).inflate(layoutIdLoading, null, false)
        root?.removeAllViews()
        root?.addView(loadingView)
    }
}