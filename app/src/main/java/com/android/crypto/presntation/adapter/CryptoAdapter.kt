package com.android.crypto.presntation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.crypto.R
import com.android.crypto.databinding.ItemCryptoBinding
import com.android.crypto.domain.entites.CoinsEntity
import com.android.crypto.presntation.utils.OnClickItemCrypto

class CryptoAdapter(private val onClickItemCrypto: OnClickItemCrypto) :
    ListAdapter<CoinsEntity, CryptoAdapter.CryptoViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<CoinsEntity>() {
        override fun areItemsTheSame(oldItem: CoinsEntity, newItem: CoinsEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CoinsEntity, newItem: CoinsEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder(
            ItemCryptoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bindData(currentList[position])
    }


    inner class CryptoViewHolder(private val binding: ItemCryptoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(coinsEntity: CoinsEntity) {
            binding.tvItemCryptoRank.text = "${coinsEntity.rank} -"
            binding.tvItemCryptoSymbol.text = coinsEntity.symbol
            binding.tvItemCryptoName.text = coinsEntity.name
            isActiveCoin(coinsEntity.is_active)

            binding.root.setOnClickListener {
                onClickItemCrypto.onItemClick(coinsEntity.id)
            }
        }

        private fun isActiveCoin(value: Boolean) {
            if (value) {
                binding.tvItemCryptoIsActive.text = "active"
                binding.tvItemCryptoIsActive.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.color_active
                    )
                )

            } else {
                binding.tvItemCryptoIsActive.text = "inactive"
                binding.tvItemCryptoIsActive.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.color_inactive
                    )
                )

            }
        }
    }


}