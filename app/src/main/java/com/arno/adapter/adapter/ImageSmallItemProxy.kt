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

class ImageSmallItemProxy : MultiAdapter.Proxy<Image, ImageSmallViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contentView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_small, parent, false)
        return ImageSmallViewHolder(contentView)
    }

    override fun onBindViewHolder(
        holderSmall: ImageSmallViewHolder,
        data: Image,
        index: Int,
        action: ((Any?) -> Unit)?
    ) {
        val id = holderSmall.itemView.findViewById<TextView>(R.id.id)
        val position = holderSmall.itemView.findViewById<TextView>(R.id.position)
        id.text = data.id
        /**
         * 此处注意由于界面目前没用使用[RecyclerView.Adapter.notifyDataSetChanged]
         * 而是其他优化的局部更新方式 那么布局中的静态逻辑只会调用一次 而不会重复渲染
         * 此处应看需求是否需要
         */
        position.text = holderSmall.adapterPosition.toString()
    }
}


class ImageSmallViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
