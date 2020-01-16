package com.mte.infrastructurebase.forms.fields.attachments.doc_attachment

import android.content.Context
import com.mte.infrastructurebase.R
import com.mte.infrastructurebase.base.BaseRVAdapter
import com.mte.infrastructurebase.data.source.remote.Resource
import com.mte.infrastructurebase.databinding.ItemAttachmentBinding
import com.mte.infrastructurebase.databinding.ItemDocAttachmentBinding
import com.mte.infrastructurebase.forms.fields.attachments.AttachItemModel
import com.mte.infrastructurebase.forms.fields.attachments.OnAttachAction

class DocAttachmentsAdapter(ctx: Context?, val onAttachAction : OnAttachAction) : BaseRVAdapter<AttachItemModel, ItemDocAttachmentBinding>(ctx) {


    val items = HashMap <String , AttachItemModel>()

    init {
        resource = Resource.success(ArrayList<AttachItemModel>())
    }


    override fun bindDataViewHolder(
        binding: ItemDocAttachmentBinding?,
        item: AttachItemModel?,
        position: Int
    ) {
        binding?.model = item
        binding?.pos = position
        binding?.onAttachAction = onAttachAction
    }

    override fun onRetry() {}

    fun addItems(items: List<AttachItemModel?>) {
        items.forEachIndexed { index, s ->
            insertItem(index , s)
        }
    }

    private fun insertItem(index: Int, item: AttachItemModel?) {
        if(item == null || items.containsKey(item.filePath)) return

        resource?.data?.add(item)
        val pos =  itemCount -1
        items[item.filePath?:""] = item

        notifyItemInserted(itemCount)
    }

    fun deleteItem(pos : Int , item: AttachItemModel) {

        if(pos in 0 until itemCount){
            resource?.data?.removeAt(pos)
            notifyItemRemoved(pos)
            items.remove(item.filePath)
            notifyDataSetChanged()
        }

    }

    override val itemLayoutRes: Int
        get() = R.layout.item_doc_attachment
}
