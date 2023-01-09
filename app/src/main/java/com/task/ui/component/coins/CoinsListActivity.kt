package com.task.ui.component.coins

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.task.R
import com.task.data.Resource
import com.task.data.dto.coin.Coins
import com.task.databinding.HomeActivityBinding
import com.task.ui.base.BaseActivity
import com.task.ui.component.coins.adapter.CoinsAdapter
import com.task.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CoinsListActivity : BaseActivity() {
    private lateinit var binding: HomeActivityBinding

    private val coinsListViewModel: CoinsListViewModel by viewModels()
    private lateinit var coinsAdapter: CoinsAdapter

    override fun initViewBinding() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.title_activity_coins)
        val layoutManager = LinearLayoutManager(this)
        binding.rvCoinsList.layoutManager = layoutManager
        binding.rvCoinsList.setHasFixedSize(true)
        coinsListViewModel.getCoins()
    }


    private fun bindListData(coins: Coins) {
        if (!(coins.coinsList.isNullOrEmpty())) {
            coinsAdapter = CoinsAdapter(coinsListViewModel, coins.coinsList)
            binding.rvCoinsList.adapter = coinsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvCoinsList.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvCoinsList.toGone()
    }


    private fun handleCoinsList(status: Resource<Coins>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(coins = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { coinsListViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(coinsListViewModel.coinsLiveData, ::handleCoinsList)
        observeSnackBarMessages(coinsListViewModel.showSnackBar)
        observeToast(coinsListViewModel.showToast)

    }
}
