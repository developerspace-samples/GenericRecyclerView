package com.developerspace.genericrv.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.developerspace.genericrv.recyclerview.header.StickyHeaderAdapter

class GenericAdapter(var onRecyclerItemListener: OnRecyclerListListener)
    : RecyclerView.Adapter<GenericAdapter.ItemViewHolder>(),
    StickyHeaderAdapter<GenericAdapter.InitialHolder> {

    var itemList : ArrayList<Any> = ArrayList()

    var currentItem : Any? = null

    val layout : Int = onRecyclerItemListener.getItemLayout()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val customRowBinding : ViewDataBinding = DataBindingUtil
            .inflate(layoutInflater,layout,parent,false)
        return ItemViewHolder(customRowBinding)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        Log.e( "onBindViewHolder: ",item.toString())
        onRecyclerItemListener.onItemSet(holder.mDataBinding,item)
        holder.mDataBinding.root.setOnClickListener {
            onRecyclerItemListener.onItemClick(holder.mDataBinding,item)
        }

        holder.mDataBinding.root.setOnLongClickListener {
            onRecyclerItemListener.onItemLongClick(holder.mDataBinding,item)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    override fun onCreateHeaderViewHolder(var1: ViewGroup?): InitialHolder {
        return InitialHolder(LayoutInflater.from(var1!!.context).inflate(android.R.layout.simple_list_item_1, var1, false))
    }

    override fun onBindHeaderViewHolder(var1: Any, var2: Int, var3: Long) {
        val item = itemList[var2]
        var initialHolder = var1 as InitialHolder;
        onRecyclerItemListener.onHeaderSet(initialHolder.textView,item)
    }

    override fun getHeaderId(var1: Int): Long {
        val item = itemList[var1]
        return onRecyclerItemListener.getHeaderId(var1,item)
    }

    class ItemViewHolder(var customRowBinding: ViewDataBinding) : RecyclerView.ViewHolder(customRowBinding.root) {
        val mDataBinding = customRowBinding
    }

    inner class InitialHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(android.R.id.text1)
    }
}