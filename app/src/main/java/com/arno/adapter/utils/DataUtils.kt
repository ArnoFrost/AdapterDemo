package com.arno.adapter.utils

import com.arno.adapter.bean.Image
import com.arno.adapter.bean.User
import java.util.*

/**
 * <pre>
 * author: xuxin
 * time  : 2020/11/11
 * desc  : 构造一个布局
</pre> *
 */
object DataUtils {
    fun getRandomUser(multiType: Boolean): User {
        return User(
            "Arno" + System.currentTimeMillis(),
            UUID.randomUUID().toString(),
            Random().nextInt(25),
            if (multiType) {
                Random().nextInt(2)
            } else {
                1
            },
            ColorUtils.getRandomColor()
        )
    }

    fun getRandomImage(multiType: Boolean): Image {
        return Image(
            "Image" + System.currentTimeMillis(),
            UUID.randomUUID().toString(),
            if (multiType) {
                Random(System.currentTimeMillis()).nextInt(2)
            } else {
                1
            }, ColorUtils.getRandomColor()
        )
    }
}