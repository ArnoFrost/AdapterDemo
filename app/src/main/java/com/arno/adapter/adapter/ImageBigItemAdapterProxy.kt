package com.arno.adapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.arno.adapter.R
import com.arno.adapter.bean.Image
import com.arno.multiadapter.MultiAdapter

class ImageBigItemAdapterProxy : MultiAdapter.AdapterProxy<Image, ImageBigViewHolder>() {
    override fun onProxyCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contentView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_big, parent, false)
        return ImageBigViewHolder(contentView)
    }

    override fun onProxyBindViewHolder(
        holderBig: ImageBigViewHolder,
        data: Image,
        index: Int,
        action: ((Any?) -> Unit)?
    ) {
        val id = holderBig.itemView.findViewById<TextView>(R.id.id)
        val position = holderBig.itemView.findViewById<TextView>(R.id.position)
        id.text = data.id
        /**
         * 此处注意由于界面目前没用使用[RecyclerView.Adapter.notifyDataSetChanged]
         * 而是其他优化的局部更新方式 那么布局中的静态逻辑只会调用一次 而不会重复渲染
         * 此处应看需求是否需要
         */
        position.text = holderBig.adapterPosition.toString()
    }
}


class ImageBigViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
