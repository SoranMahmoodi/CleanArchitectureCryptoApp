package com.android.crypto.presntation.ui.fragment.coin

import android.content.Context
import android.nfc.TagLostException
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.crypto.R
import com.android.crypto.databinding.FragmentCoinBinding
import com.android.crypto.databinding.FragmentCoinsBinding
import com.android.crypto.domain.entites.CoinEntity
import com.android.crypto.domain.entites.Tag
import com.android.crypto.presntation.adapter.TeamAdapter
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CoinFragment : Fragment() {

    private lateinit var viewModel: CoinViewModel
    private var binding: FragmentCoinBinding? = null

    private var coinId: String? = null

    private lateinit var teamsAdapter : TeamAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        coinId = arguments?.getString("coinId")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoinBinding.inflate(inflater, container, false)
        return requireNotNull(binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[CoinViewModel::class.java]
        getCoin()
        setupViews()
    }

    private fun setupViews() {
        teamsAdapter = TeamAdapter()

        binding!!.rvFragmentCoinTeams.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter =teamsAdapter
        }
    }

    private fun getCoin() {
        coinId?.let {
            viewModel.getCoin(it)
        }
        viewModel.coin.flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED).onEach {
            Log.i("CoinFra", "getCoin: $it")
            setupInfoCoin(it)
            it.tags?.let {
                setupChip(it)

            }
            it.team?.let{
                teamsAdapter.submitList(it)
            }
        }.launchIn(lifecycleScope)
    }

    private fun setupInfoCoin(coinEntity: CoinEntity) {
        binding!!.tvFragmentCoinRank.text = "${coinEntity.rank} -"
        binding!!.tvFragmentCoinSymbol.text = coinEntity.symbol
        binding!!.tvFragmentCoinDescription.text = coinEntity.description
        binding!!.tvFragmentCoinName.text = coinEntity.name
        if (coinEntity.is_active) {
            binding!!.tvFragmentCoinIsActive.text = "active"
            binding!!.tvFragmentCoinIsActive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_active
                )
            )

        } else {
            binding!!.tvFragmentCoinIsActive.text = "inactive"
            binding!!.tvFragmentCoinIsActive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_inactive
                )
            )

        }
    }

    private fun setupChip(nameTage: List<Tag>) {
        nameTage.forEach {
            val chip = Chip(requireContext())
            chip.text = it.name
            chip.textSize = 12f
            chip.chipStrokeWidth = 4f
            chip.setChipBackgroundColorResource(android.R.color.transparent)
            chip.setChipStrokeColorResource(R.color.color_chip_stroke)
            binding!!.cgFragmentCoin.addView(chip)
        }
    }


}