package com.developerspace.genericrv.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.developerspace.genericrv.R
import com.developerspace.genericrv.recyclerview.header.StickyHeaderDecoration

class GenericRecyclerView : RecyclerView {

    var customRecyclerAdapter : GenericAdapter? = null

    var itemLayout : Int? = null

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context,attributeSet) {
        setAttributes(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet,defStyle : Int) :
            super(context,attributeSet,defStyle) {
                setAttributes(attributeSet)
    }

    fun setAttributes(attributeSet: AttributeSet) {
        val a = context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.CustomRecyclerView,
            0, 0)
            /*
             * Get the shape and set shape field accordingly
             * */
    }

    fun initialize(onRecyclerListener: OnRecyclerListListener){
        if (customRecyclerAdapter==null)
            customRecyclerAdapter = GenericAdapter(onRecyclerListener)

        val stickyHeaderDecoration = StickyHeaderDecoration(customRecyclerAdapter!!)
        this.addItemDecoration(stickyHeaderDecoration, 0)
        this.adapter = customRecyclerAdapter
     }

    fun setList(list : ArrayList<*>) {
        customRecyclerAdapter?.itemList = list as ArrayList<Any>
    }

}