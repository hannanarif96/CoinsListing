package com.task.ui.component.coins.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.coin.Coin
import com.task.databinding.ItemCoinBinding
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.coins.CoinsListViewModel


class CoinsAdapter(
    private val coinsListViewModel: CoinsListViewModel,
    private val coins: List<Coin>
) : RecyclerView.Adapter<CoinViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(coin: Coin) {
            Log.d("Item_Click_Listener", coin.name ?: "")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val itemBinding =
            ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return coins.size
    }
}
