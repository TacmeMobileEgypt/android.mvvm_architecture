package com.mte.infrastructurebase.base.base_activity

import android.view.ViewGroup

interface IWrapEmptyData {

    fun addEmptyView(root: ViewGroup? , msge : String? = null , onRetryClick : OnRetryClick)
    fun addEmptyView(root: ViewGroup? , onRetryClick : OnRetryClick)
}