package com.hamdy.infrastructurebase.defaults

import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.hamdy.infrastructurebase.R
import com.hamdy.infrastructurebase.base.base_activity.IDialogLoading

class DefaultDialogLoading(val context: Context) :
    IDialogLoading {

    private lateinit var loading: AlertDialog
    var isCancelable : Boolean = false

    var themeId : Int = -1

    var layoutIdLoading : Int = R.layout.layout_loading_dialog_default

    init {
        init()
    }

    override fun init() {

        val builder: AlertDialog.Builder = if (themeId != -1)
            AlertDialog.Builder(context, themeId) else AlertDialog.Builder(context)

        builder.setCancelable(isCancelable)
        builder.setView(layoutIdLoading)
        loading = builder.create()
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(loading.window?.getAttributes())
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        loading.window?.attributes = lp
//        loading.window?.setBackgroundDrawableResource(android.R.color.transparent)
        loading.window?.setGravity(Gravity.CENTER)
    }

    override fun showLoading() {
        if (!loading.isShowing) {
            loading.show()
        }
    }

    override fun hideLoading() {
        if (loading.isShowing) {
            loading.dismiss()
        }
    }
}