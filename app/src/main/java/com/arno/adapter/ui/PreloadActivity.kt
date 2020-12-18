package com.arno.adapter.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arno.adapter.R
import com.arno.adapter.adapter.UserAdvanceItemProxy
import com.arno.adapter.adapter.UserSimpleItemProxy
import com.arno.adapter.databinding.PreloadActivityBinding
import com.arno.adapter.utils.DataUtils
import com.arno.adapter.utils.setOnItemClickListener
import com.arno.adapter.widget.varietyadapter.VarietyAdapter
import kotlinx.coroutines.*

class PreloadActivity : AppCompatActivity() {

    private lateinit var mBinding: PreloadActivityBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var simpleAdapter: VarietyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //创建DataBinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.preload_activity)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initView() {
        mBinding.addItem.setOnClickListener { addItem() }
        mBinding.removeItem.setOnClickListener { removeItem() }
        mBinding.randomItem.setOnClickListener { randomItem() }
        //垂直布局
        layoutManager = LinearLayoutManager(this)
    }


    private fun addItem() {
//        Timber.d("addItem: ")
        //添加数据
        val newList = simpleAdapter.dataList.toMutableList().apply {
            add(0, DataUtils.getRandomUser(true))
        }
        simpleAdapter.dataList = newList

    }

    private fun removeItem() {
//        Timber.d("removeItem: ")
        //随机删除数据
        if (simpleAdapter.dataList.isNotEmpty()) {
            val newList = simpleAdapter.dataList.toMutableList().apply {
                removeAt(simpleAdapter.dataList.lastIndex)
            }
            simpleAdapter.dataList = newList
        }

    }

    private fun randomItem() {
        val newList = simpleAdapter.dataList.toMutableList().apply {
            shuffle()
        }
        simpleAdapter.dataList = newList
    }

    private fun initData() {
        //初始化Adapter数据
        simpleAdapter = VarietyAdapter().apply {
            /**
             *  优化添加至一级缓存 注意要覆写[RecyclerView.Adapter.getItemId]
             *  理论上由于增加了一层可用过id查找ViewHolder所以增加了缓存的命中率 提升性能
             *  同时使得[RecyclerView.Adapter.notifyDataSetChanged]的重量级更新有了动画效果
             *  满足一些极端的业务逻辑要求,但不建议使用太多的全局更新要看业务定义
             */

//            setHasStableIds(true)

            //1. 添加项目
            addProxy(UserSimpleItemProxy())
            addProxy(UserAdvanceItemProxy())
            //2. 初始化数据
            dataList = listOf(
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
            )

            //3. 设置预载机制
            preloadItemCount = 2

            //回调创建是在onBindView处调用,避免了重复调用
            onPreload = {
                val oldList = dataList
                Log.d(TAG, "onPreload: ")
                Toast.makeText(this@PreloadActivity, "预载", Toast.LENGTH_SHORT).show()
                GlobalScope.launch(Dispatchers.IO) {
                    val newDataList = oldList.toMutableList().apply {
                        addAll(
                            listOf(
                                DataUtils.getRandomUser(true),
                                DataUtils.getRandomUser(true),
                                DataUtils.getRandomUser(true),
                                DataUtils.getRandomUser(true),
                                DataUtils.getRandomUser(true),
                                DataUtils.getRandomUser(true),
                                DataUtils.getRandomUser(true),
                                DataUtils.getRandomUser(true),
                                DataUtils.getRandomUser(true),
                            )
                        )

                    }
                    //模拟加载数据
                    delay(1000)
                    withContext(Dispatchers.Main) {
                        dataList = newDataList
                    }
                }
            }
        }

        //设置Rv相关属性
        mBinding.rvList.apply {
            //设置点击方法
            setOnItemClickListener { item, adapterPosition ->
                Toast.makeText(
                    this@PreloadActivity,
                    "adapterPosition $adapterPosition",
                    Toast.LENGTH_SHORT
                ).show()
            }
//            Timber.d("initData: ")
            /**
             * use this setting to improve performance if you know that changes
             * in content do not change the layout size of the RecyclerView
             * 如果rv的大小不会随内容改变则可以开启这个优化点 然后使用Adapter的增删改方法来更新
             * 只有需要改变的时候再去用[RecyclerView.Adapter.notifyDataSetChanged]来改变
             * 以此来优化性能 详见[RecyclerView.setHasFixedSize]
             */
//            setHasFixedSize(true)

            // use a linear layout manager
            //注意相同名称时候引用值作用域区分
            layoutManager = this@PreloadActivity.layoutManager
            // specify an viewAdapter (see also next example)
            adapter = simpleAdapter

            /**
             *  设置缓存大小 默认为2[RecyclerView.Recycler.DEFAULT_CACHE_SIZE]
             */
//            setItemViewCacheSize(3)
        }
    }

    companion object {
        const val TAG: String = "PreloadActivity"
    }

}