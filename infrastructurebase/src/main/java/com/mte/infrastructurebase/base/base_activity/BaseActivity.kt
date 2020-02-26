@file:Suppress("UNCHECKED_CAST")

package com.mte.infrastructurebase.base.base_activity

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
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.base.BaseDialog
import com.mte.infrastructurebase.defaults.*
import com.mte.infrastructurebase.utils.KeyboardUtils
import com.mte.infrastructurebase.utils.LocaleHelperJava

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {


    protected var dialogLoading: IDialogLoading? = null
    protected var dialogAlert: IDialogAlert?      = null
    protected var wrapLoading: IWrapLoading?      = null
    protected var wrapError: IWrapError?          = null
    protected var wrapEmptyData: IWrapEmptyData?  = null


    lateinit var binding: T

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract fun initUI(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        LocaleHelperJava.onAttach(this)
        super.onCreate(savedInstanceState)
        init()
        performDataBinding()
        initUI(savedInstanceState)
    }

    open fun init() {
        dialogLoading   = DefaultDialogLoading(this)
        dialogAlert     = DefaultDialogAlert(this , getString(R.string.ok),getString(R.string.cancel))
        wrapLoading     = DefaultWrapLoading(this)
        wrapError       = DefaultWrapError(this)
        wrapEmptyData   = DefaultWrapEmptyData(this)
    }


    protected open fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.executePendingBindings()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelperJava.onAttach(newBase))
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
        addFragment(supportFragmentManager , fragment , id , addToBackStack)
    }


    //Add Fragment by fragmentManager
    fun addFragment(fragmentManager : FragmentManager,
                    fragment : Fragment,
                    id: Int,
                    addToBackStack: Boolean) {

        val transaction = fragmentManager.beginTransaction()

        transaction.add(id, fragment, fragment.javaClass.simpleName)

        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.simpleName)

        transaction.commit()

    }

    fun replaceFragment(fragment: Fragment, id: Int, addToBackStack: Boolean) {
        replaceFragment(supportFragmentManager , fragment , id , addToBackStack)
    }

    fun replaceFragment( fragmentManager : FragmentManager ,
                         fragment: Fragment,
                         id: Int,
                         addToBackStack: Boolean) {

        val transaction = fragmentManager.beginTransaction()


        if (addToBackStack)
            transaction.addToBackStack(fragment.javaClass.canonicalName)

        transaction.replace(id, fragment, fragment.javaClass.canonicalName)
        transaction.commit()
    }

    /**
     * show message dialog
     */
    fun showInfoMsgDialog(message: String?) {
        if(message == null) return

        runOnUiThread { dialogAlert?.showInfoMsg(message) }
    }

    /**
     * show message dialog
     */
    fun showSuccessMsgDialog(message: String?) {
        if(message == null) return
        runOnUiThread { dialogAlert?.showSuccessMsg(message) }
    }
    fun showSuccessMsgDialog(message: String? ,onYesClick:()->Unit) {
        if(message == null) return
        runOnUiThread { dialogAlert?.showSuccessMsg(message,onYesClick = onYesClick) }
    }

    /**
     * show Error message dialog
     */
    fun showErrorMsgDialog(message: String?) {
        if(message == null) return
        runOnUiThread { dialogAlert?.showErrorMsg(message)

        }
    }

    /**
     * show Warning message dialog
     */
    fun showWarningMsgDialog(message: String?) {
        if(message == null) return
        runOnUiThread { dialogAlert?.showWarningMsg(message) }
    }

    /**
     * Show confirm message dialog loading
     */
    fun showConfirmMessagDialog(message: String?, yesAction: () -> Unit) {
        if(message == null) return
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
                wrapEmptyData?.addEmptyView(root)
            else wrapEmptyData?.addEmptyView(root , msg)
        }

    }


    fun openDialogFragment(dialogListFragment: BaseDialog<*>) {
        val ft = supportFragmentManager.beginTransaction()
        dialogListFragment.show(ft, "dialog")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        supportFragmentManager.fragments.forEach {
            it.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


}