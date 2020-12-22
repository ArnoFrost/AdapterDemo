package com.arno.adapter.bean

import com.arno.adapter.adapter.UserAdvanceItemProxy
import com.arno.adapter.adapter.UserSimpleItemProxy
import com.arno.multiadapter.Diff
import com.arno.multiadapter.MultiAdapter
import java.io.Serializable

/**
 * <pre>
 * author: xuxin
 * time  : 2020/11/11
 * desc  :
</pre> *
 */
data class User(
    var name: String,
    var id: String,
    var age: Int,
    var type: Int,
    var color: Int
) : Serializable, Diff, MultiAdapter.DataProxyMap {
    override fun hashCode(): Int = this.id.hashCode()
    override fun equals(other: Any?): Boolean = (other as? User)?.name == this.name
    override fun diff(other: Any?): Any? {
        return when {
            other !is User -> null
            this.id != other.id -> {
                "text change"
            }
            else -> null
        }
    }

    override fun toProxy(): String {
        return when (type) {
            1 -> UserSimpleItemProxy::class.java.toString()
            else -> UserAdvanceItemProxy::class.java.toString()
        }
    }
}