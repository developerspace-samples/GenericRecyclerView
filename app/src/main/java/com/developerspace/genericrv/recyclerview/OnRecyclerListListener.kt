package com.developerspace.genericrv.recyclerview

import android.widget.TextView
import androidx.databinding.ViewDataBinding

interface OnRecyclerListListener {
    fun onItemSet(mItemBinding: ViewDataBinding, item: Any)
    fun onHeaderSet(textView: TextView,any: Any)
    fun getItemLayout(): Int
    fun getHeaderId(var1: Int, item: Any): Long
    fun onItemClick(mDataBinding: ViewDataBinding, item: Any)
    fun onItemLongClick(mDataBinding: ViewDataBinding, item: Any)
}