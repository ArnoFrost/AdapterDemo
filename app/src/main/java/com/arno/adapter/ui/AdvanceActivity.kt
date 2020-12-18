package com.arno.adapter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arno.adapter.R
import com.arno.adapter.adapter.ImageBigItemProxy
import com.arno.adapter.adapter.ImageSmallItemProxy
import com.arno.adapter.adapter.UserAdvanceItemProxy
import com.arno.adapter.adapter.UserSimpleItemProxy
import com.arno.adapter.databinding.AdvanceActivityBinding
import com.arno.adapter.utils.DataUtils
import com.arno.adapter.utils.setOnItemClickListener
import com.arno.adapter.widget.varietyadapter.VarietyAdapter
import kotlinx.coroutines.*
import kotlin.random.Random

class AdvanceActivity : AppCompatActivity() {

    private lateinit var mBinding: AdvanceActivityBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var simpleAdapter: VarietyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //创建DataBinding
        mBinding = DataBindingUtil.setContentView(this, R.layout.advance_activity)
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
        //添加数据 随机一种数据
        val index = Random.nextInt(simpleAdapter.dataList.size)

        val newList = simpleAdapter.dataList.toMutableList().apply {
            if (Random.nextBoolean()) {
                add(index, DataUtils.getRandomUser(true))
            } else {
                add(index, DataUtils.getRandomImage(true))
            }
        }
        simpleAdapter.dataList = newList
        mBinding.rvList.post {
            mBinding.rvList.smoothScrollToPosition(index)
        }

    }

    private fun removeItem() {
        //随机删除数据
        if (simpleAdapter.dataList.isNotEmpty()) {
            val index = simpleAdapter.dataList.lastIndex
            val newList = simpleAdapter.dataList.toMutableList().apply {
                removeAt(index)
            }
            simpleAdapter.dataList = newList
            mBinding.rvList.post {
                mBinding.rvList.smoothScrollToPosition(index)
            }

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
            //1. 添加VH项目 多种
            addProxy(UserSimpleItemProxy())
            addProxy(UserAdvanceItemProxy())
            addProxy(ImageSmallItemProxy())
            addProxy(ImageBigItemProxy())

            //2. 初始化数据
            dataList = listOf(
                DataUtils.getRandomUser(true),
                DataUtils.getRandomUser(true),
                DataUtils.getRandomImage(true),
                DataUtils.getRandomImage(true),
            )
        }

        //设置Rv相关属性
        mBinding.rvList.apply {
            //设置点击方法
            setOnItemClickListener { item, adapterPosition ->
                Toast.makeText(
                    this@AdvanceActivity,
                    "adapterPosition $adapterPosition",
                    Toast.LENGTH_SHORT
                ).show()
            }
            /**
             * use this setting to improve performance if you know that changes
             * in content do not change the layout size of the RecyclerView
             * 如果rv的大小不会随内容改变则可以开启这个优化点 然后使用Adapter的增删改方法来更新
             * 只有需要改变的时候再去用[RecyclerView.Adapter.notifyDataSetChanged]来改变
             * 以此来优化性能 详见[RecyclerView.setHasFixedSize]
             */
//            setHasFixedSize(true)

            //注意相同名称时候引用值作用域区分
            layoutManager = this@AdvanceActivity.layoutManager
            // specify an viewAdapter (see also next example)
            adapter = simpleAdapter
        }
    }

    companion object {
        const val TAG: String = "AdvanceActivity"
    }

}