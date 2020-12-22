package com.arno.adapter.bean

import com.arno.adapter.adapter.ImageBigItemProxy
import com.arno.adapter.adapter.ImageSmallItemProxy
import com.arno.multiadapter.Diff
import java.io.Serializable

/**
 * <pre>
 * author: xuxin
 * time  : 2020/11/11
 * desc  : 图片显示项目
</pre> *
 */
data class Image(
    var name: String,
    var id: String,
    var type: Int,
    var color: Int
) : Serializable, Diff, com.arno.multiadapter.MultiAdapter.DataProxyMap {
    override fun hashCode(): Int = this.id.hashCode()
    override fun equals(other: Any?): Boolean = (other as? Image)?.name == this.name
    override fun diff(other: Any?): Any? {
        return when {
            other !is Image -> null
            this.id != other.id -> {
                "text change"
            }
            else -> null
        }
    }

    override fun toProxy(): String {
        return when (type) {
            1 -> ImageBigItemProxy::class.java.toString()
            else -> ImageSmallItemProxy::class.java.toString()
        }
    }
}