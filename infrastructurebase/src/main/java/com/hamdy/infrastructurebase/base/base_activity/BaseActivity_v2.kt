@file:Suppress("UNCHECKED_CAST")

package com.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.fdf.utils.LocaleHelper
import com.hamdy.infrastructurebase.base.BaseDialog
import com.hamdy.infrastructurebase.base.BaseViewModel
import com.hamdy.infrastructurebase.base.base_activity.*
import com.hamdy.infrastructurebase.defaults.*
import com.hamdy.infrastructurebase.utils.KeyboardUtils

abstract class BaseActivity_v2<T : ViewDataBinding> : AppCompatActivity() {


    private var dialogLoading: IDialogLoading? = null
    private var dialogAlert: IDialogAlert?      = null
    private var wrapLoading: IWrapLoading?      = null
    private var wrapError: IWrapError?          = null
    private var wrapEmptyData: IWrapEmptyData?  = null

    val viewmodels : HashMap<Class<*> , BaseViewModel?>? = HashMap()


    lateinit var binding: T

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract fun initUI(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        performDataBinding()
        initUI(savedInstanceState)
    }

    private fun init() {
        dialogLoading   = DefaultDialogLoading(this)
        dialogAlert     = DefaultDialogAlert(this)
        wrapLoading     = DefaultWrapLoading(this)
        wrapError       = DefaultWrapError(this)
        wrapEmptyData   = DefaultWrapEmptyData(this)
    }



    fun <VT : BaseViewModel> getViewModel(viewmodelClass : Class<*>): VT? {
        return  viewmodels?.get(viewmodelClass) as VT
    }
    protected open fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.executePendingBindings()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }


    /**
     * Show dialog loading
     */
    open fun showDialogLoading() {
        runOnUiThread {
            dialogLoading?.showLoading()
        }
    }


    /**
     * Hide dialog loading
     */
    open fun hideDialogLoading() {
        runOnUiThread {
            dialogLoading?.hideLoading()
        }
    }


    fun hideKeyboard() {
        KeyboardUtils.hideKeyboard(this)
    }

    fun hideKeyboardOutSide(view: View) {
        KeyboardUtils.hideKeyBoardWhenClickOutSide(view, this)
    }

    fun hideKeyboardOutSideText(view: View) {
        KeyboardUtils.hideKeyBoardWhenClickOutSideText(view, this)
    }


    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView,
        adapter: RecyclerView.Adapter<VH>
    ) {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter
    }

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView, adapter:
        RecyclerView.Adapter<VH>,
        isHasFixedSize: Boolean,
        isNestedScrollingEnabled: Boolean
    ) {
        rcv.setHasFixedSize(isHasFixedSize)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter
        rcv.isNestedScrollingEnabled = isNestedScrollingEnabled
    }

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView,
        adapter: RecyclerView.Adapter<VH>,
        isNestedScrollingEnabled: Boolean
    ) {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = LinearLayoutManager(this)
        rcv.adapter = adapter
        rcv.isNestedScrollingEnabled = isNestedScrollingEnabled
    }

    open fun clearAllBackStack() {
        val fm = supportFragmentManager
        for (i in 0 until fm.backStackEntryCount) {
            fm.popBackStack()
        }
    }


    fun addFragment(fragment: Fragment, id: Int, addToBackStack: Boolean) {

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        transaction.add(id, fragment, fragment.javaClass.simpleName)

        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.simpleName)

        transaction.commit()

    }


    fun replaceFragment(fragment: Fragment, id: Int, addToBackStack: Boolean) {

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()


        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.canonicalName)

        transaction.replace(id, fragment, fragment.javaClass.canonicalName)
        transaction.commit()
    }

    /**
     * show message dialog
     */
    fun showInfoMsgDialog(message: String) {

        runOnUiThread { dialogAlert?.showInfoMsg(message) }
    }

    /**
     * show Error message dialog
     */
    fun showErrorMsgDialog(message: String) {
        runOnUiThread { dialogAlert?.showErrorMsg(message) }
    }

    /**
     * show Warning message dialog
     */
    fun showWarningMsgDialog(message: String) {
        runOnUiThread { dialogAlert?.showWarningMsg(message) }
    }

    /**
     * Show confirm message dialog loading
     */
    fun showConfirmMessagDialog(message: String, yesAction: () -> Unit) {
        runOnUiThread {
            dialogAlert?.showConfirmationMsg(message, object : ConfirmHandler {
                override fun onConfirmed() {
                    yesAction.invoke()
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        supportFragmentManager.fragments.forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
    }


    fun wrapingLoading(root: ViewGroup?) {
        runOnUiThread {
            wrapLoading?.addLoadingView(root)
        }

    }

    fun wrapinError(
        msg: String? = null,
        root: ViewGroup?,
        onRetryClick: (()->Unit)?  = null
    ) {

        runOnUiThread {
            wrapError?.addErrorView(root , msg  , object : OnRetryClick{
                override fun onRetry() {
                    onRetryClick?.invoke()
                }

            })
        }

    }

    fun wrapinEmptyData(
        msg: String? = null,
        root: ViewGroup?,
        onRetryClick: (()->Unit)?  = null
    ) {

        runOnUiThread {

            if(msg == null)
                wrapEmptyData?.addEmptyView(root , object : OnRetryClick{
                    override fun onRetry() {
                        onRetryClick?.invoke()
                    }

                })
            else wrapEmptyData?.addEmptyView(root , msg  , object : OnRetryClick{
                override fun onRetry() {
                    onRetryClick?.invoke()
                }

            })
        }

    }




    fun openDialogFragment(dialogListFragment: BaseDialog<*>) {
        val ft = supportFragmentManager.beginTransaction()
        dialogListFragment.show(ft, "dialog")
    }

}