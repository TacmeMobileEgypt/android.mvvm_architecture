package com.hamdy.infrastructurebase.base.base_activity

import android.view.ViewGroup

interface IWrapError {

    fun addErrorView(root: ViewGroup?, msge: String?, onRetryClick: OnRetryClick)
}