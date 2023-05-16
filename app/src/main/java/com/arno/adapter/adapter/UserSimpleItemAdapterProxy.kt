package com.arno.adapter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.arno.adapter.R
import com.arno.adapter.bean.User
import com.arno.multiadapter.MultiAdapter

class UserSimpleItemAdapterProxy : MultiAdapter.AdapterProxy<User, UserSimpleViewHolder>() {
    override fun onProxyCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contentView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_simple, parent, false)
        return UserSimpleViewHolder(contentView)
    }

    override fun onProxyBindViewHolder(
        holderSimple: UserSimpleViewHolder,
        data: User,
        index: Int,
        action: ((Any?) -> Unit)?
    ) {
        val name = holderSimple.itemView.findViewById<TextView>(R.id.name)
        val id = holderSimple.itemView.findViewById<TextView>(R.id.id)
        val position = holderSimple.itemView.findViewById<TextView>(R.id.position)

        name.text = data.name
        id.text = data.id
        /**
         * 此处注意由于界面目前没用使用[RecyclerView.Adapter.notifyDataSetChanged]
         * 而是其他优化的局部更新方式 那么布局中的静态逻辑只会调用一次 而不会重复渲染
         * 此处应看需求是否需要
         */
        position.text = holderSimple.adapterPosition.toString()
    }
}


class UserSimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
