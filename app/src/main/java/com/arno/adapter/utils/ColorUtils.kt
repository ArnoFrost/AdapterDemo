package com.arno.adapter.utils

import android.graphics.Color
import kotlin.random.Random

/**
 * <pre>
 *     author: xuxin
 *     time  : 2020/11/3
 *     desc  :
 * </pre>
 */
object ColorUtils {
    private val backGroundColorList = listOf(
        //红1,
        "#F44336",
        //浅蓝2
        "#2196F3",
        //氰3
        "#00BCD4",
        //柠檬4
        "#CDDC39",
        //绿5
        "#4CAF50",
        //靛青6
        "#512DA8",
//        //琥珀色4
//        "#FFC107",
        //白色4
        "#FFFFFF",
        //紫3
        "#673AB7",
        //橘色2
        "#FF9800",
        //白1
        "#FFFFFF"

    )

    private val textColorList = backGroundColorList.reversed()


    fun getRandomColor(): Int {
        return Color.parseColor(backGroundColorList[Random.nextInt(backGroundColorList.size - 1)])
    }

//    fun getRandomBackGroundColor(str: String): Int {
//        return Color.parseColor(backGroundColorList[getUniqueRandomIndexColorByString(str)])
//    }
//
//    fun getRandomTextColor(str: String): Int {
//        return Color.parseColor(textColorList[getUniqueRandomIndexColorByString(str)])
//    }
//
//    /**
//     * 自定义简单根据字符串算出一个唯一的索引值在颜色列表范围内的值
//     *
//     * @param str
//     * @return
//     */
//    fun getUniqueRandomIndexColorByString(str: String): Int {
//        return ConvertUtils.string2Bytes(str)[
//                ConvertUtils.string2Bytes(str).lastIndex
//        ].toInt() % backGroundColorList.size
//    }
}