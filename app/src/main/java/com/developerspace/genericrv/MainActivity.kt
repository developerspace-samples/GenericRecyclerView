package com.developerspace.genericrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.developerspace.genericrv.databinding.ItemLayoutBinding
import com.developerspace.genericrv.recyclerview.GenericRecyclerView
import com.developerspace.genericrv.recyclerview.OnRecyclerListListener

class MainActivity : AppCompatActivity() {
    val usersList : ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchUsers()
        val genericRecyclerView = findViewById<GenericRecyclerView>(R.id.custom_recyclerview)
        genericRecyclerView.initialize(object : OnRecyclerListListener {
            //This method is Important. It is necessary to create and return layout file
            override fun getItemLayout(): Int {
                return R.layout.item_layout
            }

            //This method is Important.
            override fun onItemSet(mItemBinding: ViewDataBinding, item: Any) {
                val binding = mItemBinding as ItemLayoutBinding
                binding.itemVariable = item as User
                Glide.with(this@MainActivity).load(item.image)
                    .diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.image)
            }

            override fun onHeaderSet(textView: TextView, any: Any) {
                textView.text = (any as User).name.substring(0,1)
            }

            override fun getHeaderId(var1: Int, item: Any): Long {
                item as User
                val name = if (item.name.toString().isNotEmpty()) item.name.toString().substring(0, 1).toUpperCase().toCharArray()[0] else '#'
                return name.toLong()
            }

            override fun onItemClick(mDataBinding: ViewDataBinding, item: Any) {
                Toast.makeText(this@MainActivity,"Item Click ${(item as User).description}",Toast.LENGTH_LONG).show()
            }

            override fun onItemLongClick(mDataBinding: ViewDataBinding, item: Any) {
                Toast.makeText(this@MainActivity,"Item Long Click ${(item as User).description}",Toast.LENGTH_LONG).show()
            }
        })
        genericRecyclerView.setList(usersList)

    }

    fun fetchUsers() {
        for (i in 1..5) {
            val user = User("https://picsum.photos/id/${i}/200/200.jpg", "A User_$i", "A User $i Description")
            usersList.add(user)
        }
        for (i in 1..5) {
            val user = User("https://picsum.photos/id/${i*6}/200/200.jpg", "B User_$i", "B User $i Description")
            usersList.add(user)
        }
    }
}