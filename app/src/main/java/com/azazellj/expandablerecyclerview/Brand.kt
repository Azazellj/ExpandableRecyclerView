package com.azazellj.expandablerecyclerview

import androidx.annotation.DrawableRes

/**
 * Created by azazellj on 11/25/19.
 */
class Brand(
    val name: String,
    @DrawableRes val image: Int,
    val address: String,
    val shops: List<Shop> = mutableListOf()
)