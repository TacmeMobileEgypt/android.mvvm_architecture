package com.mte.infrastructurebase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.BaseActivity
import com.mte.infrastructurebase.data.source.remote.Resource

abstract class BaseFragment < D : ViewDataBinding>:Fragment() {

    var isShown: Boolean = false
    protected lateinit var binding: D



    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        initUI(savedInstanceState)
        binding.executePendingBindings()
        return binding.root
    }
    protected abstract fun initUI(savedInstanceState: Bundle?)

    fun <VH : RecyclerView.ViewHolder> setUpRcv(rcv: RecyclerView, adapter: RecyclerView.Adapter<VH>) {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = LinearLayoutManager(context)
        rcv.adapter = adapter
    }

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView, adapter:
        RecyclerView.Adapter<VH>,
        isHasFixedSize: Boolean,
        isNestedScrollingEnabled: Boolean
    ) {
        rcv.setHasFixedSize(isHasFixedSize)
        rcv.layoutManager = LinearLayoutManager(context)
        rcv.adapter = adapter
        rcv.isNestedScrollingEnabled = isNestedScrollingEnabled
    }

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView, adapter:
        RecyclerView.Adapter<VH>,
        isNestedScrollingEnabled: Boolean
    ) {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = LinearLayoutManager(context)
        rcv.adapter = adapter
        rcv.isNestedScrollingEnabled = isNestedScrollingEnabled
    }

    fun addFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.addFragment(fragment , id , addToBackStack)
    }

    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.addFragment(fragmentManager ,fragment , id , addToBackStack)
    }



    fun replaceFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.replaceFragment(fragment , id , addToBackStack)
    }

    fun replaceFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.replaceFragment(fragmentManager ,fragment , id , addToBackStack)
    }

    fun hideKeyboard() {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.hideKeyboard()
            }
        }
    }

    fun showDialogLoading() {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.showDialogLoading()
            }
        }
    }

    fun hideDialogLoading() {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.hideDialogLoading()
            }
        }
    }

    fun showErrorMsgDialog(message : String?) {

        if(message == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showErrorMsgDialog(message)
            }
        }
    }

    fun showInfoMsgDialog(message : String?) {

        if(message == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showInfoMsgDialog(message)
            }
        }
    }

    fun showWarningMsgDialog(message : String?) {

        if(message == null) return

        activity?.let {
            if (it is BaseActivity<*>) {
                it.showWarningMsgDialog(message)
            }
        }
    }

    fun showConfirmMessagDialog(message : String , yesAction : () -> Unit) {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.showConfirmMessagDialog(message , yesAction)
            }
        }
    }



    fun openDialogFragment(dialogListFragment: BaseDialog<*>) {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.openDialogFragment(dialogListFragment)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        isShown = false
    }


    override fun onResume() {
        super.onResume()

        isShown = true
    }


    fun wrapingLoading(root : ViewGroup?) {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.wrapingLoading(root)
            }
        }
    }

    fun wrapingError(root : ViewGroup? ,
                     resource : Resource<*>?,
                     onRetryClick: (()->Unit)?  = null) {

        activity?.let {
            if (it is BaseActivity<*>) {
                it.wrapinError(resource?.message , root , onRetryClick )
            }
        }
    }

    fun wrapingEmtyData(
        root : ViewGroup? ,
        resource : Resource<*>? ,
        onRetryClick: (()->Unit)?  = null) {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.wrapinEmptyData(resource?.message , root , onRetryClick)
            }
        }
    }

    fun onBackbressed(): Boolean {

        var beforelasFragment : Fragment? = null

        if(isShown){
            var count = childFragmentManager.backStackEntryCount

            while (count != 0){
                val name =  childFragmentManager.getBackStackEntryAt(count-1).name
                beforelasFragment = childFragmentManager.findFragmentByTag(name)
                count = beforelasFragment?.childFragmentManager?.backStackEntryCount?: 0
            }

            if(beforelasFragment != null) {
                childFragmentManager.popBackStack()
                return true
            }
        }

        return false
    }

}