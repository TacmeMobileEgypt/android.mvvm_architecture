package com.mte.infrastructurebase.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.BaseActivity
import com.base.BaseActivity_v2

abstract class BaseFragment_v2 < D : ViewDataBinding , V : BaseViewModel>:Fragment() {

    private var baseActivity: BaseActivity_v2<*>? = null
    private  var viewModel: V? = null
    protected lateinit var dataBinding: D

    abstract val viewModelClass : Class<V>?


    @get:LayoutRes
    protected abstract val layoutRes: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)

        if(viewModelClass != null){
            viewModel =  ViewModelProviders.of(this).get(viewModelClass!!)
            baseActivity?.viewmodels?.put(viewModelClass!! , viewModel)
        }



        return dataBinding.root
    }


    fun <VT : BaseViewModel> getViewModel(viewmodelClass : Class<*>): VT? {
        return  baseActivity?.getViewModel<VT>(viewmodelClass)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
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

    fun replaceFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if(activity is BaseActivity<*>)
            activity.replaceFragment(fragment , id , addToBackStack)
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

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is BaseActivity_v2<*>)
            baseActivity = context
    }

}