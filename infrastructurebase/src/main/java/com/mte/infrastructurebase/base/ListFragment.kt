package com.mte.infrastructurebase.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.data.source.remote.Resource
import com.mte.infrastructurebase.databinding.ListItemsBinding


abstract class ListFragment<D : ViewDataBinding> : BaseFragment<D>() {

    override val layoutRes: Int
        get() = R.layout.list_items

    override fun initUI(savedInstanceState: Bundle?) {
       setupList()
    }

    fun setupList() {
        getSwipeRefreshLayout()?.setOnRefreshListener { refresh() }
        setupRecyclarView()
        getRecyclarView()?.adapter = listAdapter
    }

    abstract fun refresh()

    open fun setupRecyclarView() {
        getRecyclarView()?.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
    }

    open fun getRecyclarView(): RecyclerView?  = if(binding is ListItemsBinding) (binding as ListItemsBinding).rvList else null

    abstract val  listAdapter : BaseRVAdapter< *  , *>?

    open fun updateData(data: Resource<List<*>>?) {
        if (data == null || getRecyclarView() == null) return
        (getRecyclarView()?.adapter as BaseRVAdapter< *  , *>).submitData(data)
        updateRefreshIndicator()
    }

    protected fun  updateRefreshIndicator() {
        getSwipeRefreshLayout()?.isRefreshing = false
    }

    private fun getSwipeRefreshLayout(): SwipeRefreshLayout?  = if(binding is ListItemsBinding) (binding as  ListItemsBinding).srlList else null
}
