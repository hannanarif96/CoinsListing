package com.task.ui.base.listeners

import com.task.data.dto.coin.Coin


interface RecyclerItemListener {
    fun onItemSelected(coin : Coin)
}
