# GenericRecyclerView
[![Platform](https://img.shields.io/badge/Platform-Android-brightgreen.svg)](#)
[![Platform](https://img.shields.io/badge/Language-Kotlin-yellowgreen.svg)](#)
![GitHub stars](https://img.shields.io/github/stars/developerspace-samples/GenericRecyclerView?style=social)

GenericRecyclerView is a component which is useful when there are various of RecyclerView in single application. You can use this component with just a single method and it provides all the option which you can control. You don't need to create Adapter for each RecyclerView.


## Pre-requisites
 :heavy_check_mark: Android studio installed in your system.<br/>
 :heavy_check_mark: Android Device or Emulator to run your app.<br/>


- You can clone the project using below line.

```// Clone this repository
  git clone https://github.com/developerspace-samples/GenericRecyclerView.git
```

Once the project is cloned you can use `recyclerview` package which includes GenericRecyclerView Component.

You can add <b>GenericRecyclerView</b> in your layout file using below code.

```
 <com.developerspace.genericrv.recyclerview.GenericRecyclerView
        android:layout_width="match_parent"
        android:id="@+id/custom_recyclerview"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        android:layout_height="match_parent"/>
 
```

In your `.java` or `.kt` file add the reference of <b>GenericRecyclerView</b> and use `GenericRecyclerView.initialize()` to initialize the component. It also provides certain methods in RecyclerListListener which can help you to make modification in recyclerview items.
You can use `GenericRecyclerView.setList()` to set your list of objects in recyclerview.

<i>Note ~ You need to create and return `item_layout.xml` file in `getItemLayout()`</i>

```
        val genericRecyclerView = findViewById<GenericRecyclerView>(R.id.custom_recyclerview)
        
        genericRecyclerView.initialize(object : OnRecyclerListListener {
            override fun onHeaderSet(textView: TextView, any: Any) {
                textView.text = (any as User).name.substring(0,1)
            }

            override fun getItemLayout(): Int {
                return R.layout.item_layout
            }

            override fun onItemSet(mItemBinding: ViewDataBinding, item: Any) {
                val binding = mItemBinding as ItemLayoutBinding
                binding.itemVariable = item as User
                Glide.with(this@MainActivity).load(item.image)
                    .diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.image)
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
        
        //Set List
        genericRecyclerView.setList(usersList)
```
---

<div style="width:100%">
	<div style="width:50%; display:inline-block">
		<h2 align="center">
      :handshake: Open for Contribution
		</h2>	
	</div>	
</div>