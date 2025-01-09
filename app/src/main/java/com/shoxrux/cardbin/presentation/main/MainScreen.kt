package com.shoxrux.cardbin.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shoxrux.cardbin.R
import com.shoxrux.cardbin.data.model.CardBinResponse
import com.shoxrux.cardbin.databinding.ScreenMainBinding
import com.shoxrux.cardbin.presentation.base.BaseFragment

class MainScreen : BaseFragment(R.layout.screen_main) {

    private val viewModel: BinViewModel by viewModels()
    private val binding by viewBinding(ScreenMainBinding::bind)

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {
        setActions()
        observe()
    }

    private fun observe() {
        viewModel.success.observe(viewLifecycleOwner) {
            setIsLoading(false)
            setData(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            setIsLoading(false)
        }
    }

    private fun setData(data: CardBinResponse?) {
        val unknownText = "Неизвестно"
        binding.textScheme.text = data?.scheme ?: unknownText
        binding.textDebit.text = data?.type ?: unknownText
        binding.textBrand.text = data?.brand ?: unknownText
        binding.textCountry.text = data?.country?.name ?: unknownText
        binding.textCurrency.text = data?.country?.currency ?: unknownText
        binding.textBank.text = data?.bank?.name ?: unknownText
        binding.textUrl.text = data?.bank?.url ?: unknownText
        binding.textBankPhone.text = data?.bank?.phone ?: unknownText

    }

    private fun setActions() {
        binding.binEdt.addTextChangedListener {
            checkIsEntered()
        }
        binding.btnSearch.setOnClickListener {
            sendRequest(binding.binEdt.text?.toString()?.replace("\\s".toRegex(), "") ?: "")
        }
    }

    private fun checkIsEntered() {
        if (isValid(binding.binEdt.text.toString())) {
            binding.btnSearch.visibility = View.VISIBLE
        } else {
            binding.btnSearch.visibility = View.GONE
        }
    }

    private fun isValid(bin: String): Boolean {
        return bin.length == 7 || bin.length == 9
    }

    private fun sendRequest(bin: String) {
        viewModel.getCardBin(bin)
        setIsLoading(true)
    }

    private fun setIsLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.data.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.data.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }
}
