package com.task.ui.component.coins.adapter

import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.coin.Coin
import com.task.databinding.ItemCoinBinding
import com.task.ui.base.listeners.RecyclerItemListener

class CoinViewHolder(private val itemBinding: ItemCoinBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(coin: Coin, recyclerItemListener: RecyclerItemListener) {

        with(itemBinding) {
            tvId.text = "Id: " + coin.id
            tvCoinName.text = "Name: " + coin.name
            tvCoinSymbol.text = "Symbol: " + coin.symbol
            tvCoinRank.text = "Rank: " + coin.rank

            if (coin.isNew == true) {
                tvCoinIsNew.text = "New coin? : Yes"
            } else {
                tvCoinIsNew.text = "New coin? : No"
            }

            if (coin.isActive == true) {
                tvCoinIsActive.text = "Coin Active? : Yes"
            } else {
                tvCoinIsActive.text = "Coin Active? : No"
            }

            tvCoinType.text = "Type: " + coin.type
        }

    }
}