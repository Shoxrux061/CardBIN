package com.shoxrux.cardbin.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoxrux.cardbin.data.room.RoomArticles
import com.shoxrux.cardbin.databinding.ItemHistoryBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val data = ArrayList<RoomArticles>()

    fun setData(data: List<RoomArticles>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: RoomArticles) {
            binding.bin.text = data.bin
            binding.scheme.text = data.scheme
            binding.textUrl.text = data.urlBank
            binding.textBank.text = data.bank
            binding.textBrand.text = data.brand
            binding.textDebit.text = data.type
            binding.textCountry.text = data.country
            binding.textPrepaid.text =
                if (data.prepaid == null) "Неизвестно" else if (data.prepaid) "Да" else "Нет"
            binding.textCurrency.text = data.currency
            binding.textBankPhone.text = data.phoneBank
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

}