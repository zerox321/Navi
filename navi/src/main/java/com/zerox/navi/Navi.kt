package com.zerox.navi

sealed class Navi {
    abstract val icon: Int
    abstract val destination: Int

    data class Item(
        val title: String,
        override val icon: Int,
        override val destination: Int,
        var count: Int = 0,
    ) : Navi()

    data class ItemNoTitle(
        override val icon: Int,
        override val destination: Int,
    ) : Navi()
}
