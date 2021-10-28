package com.android.crypto.presntation.ui.fragment.coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.crypto.R
import com.android.crypto.databinding.FragmentCoinsBinding
import com.android.crypto.presntation.utils.OnClickItemCrypto
import com.android.crypto.presntation.adapter.CryptoAdapter
import com.android.crypto.presntation.ui.fragment.coin.CoinFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CoinsFragment : Fragment(), OnClickItemCrypto {

    private val viewModel: CoinsViewModel by viewModels()

    private var binding: FragmentCoinsBinding? = null

    private lateinit var cryptoAdapter: CryptoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cryptoAdapter = CryptoAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoinsBinding.inflate(inflater, container, false)
        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        getCoins()
    }

    private fun setupViews() {
        binding!!.rvFragmentCoinsCrypto.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cryptoAdapter
        }
    }

    private fun getCoins() {
        viewModel.coins.flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED).onEach {
            cryptoAdapter.submitList(it)
        }.launchIn(lifecycleScope)
    }

    private fun passCoinIdInCoinFragment(coinId: String) = CoinFragment()
        .apply {
            arguments = Bundle().apply {
                putString("coinId", coinId)
            }
        }

    override fun onItemClick(coinId: String) {
        val fragment = requireActivity().supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container_main, passCoinIdInCoinFragment(coinId))
        fragment.addToBackStack(null)
        fragment.commit()
    }
}