package com.shoxrux.cardbin.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shoxrux.cardbin.R
import com.shoxrux.cardbin.data.model.CardBinResponse
import com.shoxrux.cardbin.data.room.RoomArticles
import com.shoxrux.cardbin.data.room.RoomDao
import com.shoxrux.cardbin.databinding.ScreenMainBinding
import com.shoxrux.cardbin.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainScreen : BaseFragment(R.layout.screen_main) {

    @Inject
    lateinit var room: RoomDao
    private val viewModel: BinViewModel by viewModels()
    private val binding by viewBinding(ScreenMainBinding::bind)
    private var currentBin = ""

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {
        setActions()
        observe()
    }

    private fun observe() {
        viewModel.success.observe(viewLifecycleOwner) {
            setIsLoading(false)
            setData(it, currentBin)
            currentBin = ""
        }
        viewModel.error.observe(viewLifecycleOwner) {
            setIsLoading(false)
            currentBin = ""
        }
    }

    private fun setData(data: CardBinResponse?, bin: String) {
        val unknownText = "Неизвестно"

        data?.prepaid?.let {
            if (it) {
                binding.textPrepaid.text = "Да"
            } else {
                binding.textPrepaid.text = "Нет"
            }
        }

        binding.textScheme.text = data?.scheme ?: unknownText
        binding.textDebit.text = data?.type ?: unknownText
        binding.textBrand.text = data?.brand ?: unknownText
        binding.textCountry.text =
            data?.country?.name.plus(" / ${data?.country?.alpha2}").plus(" ${data?.country?.emoji}")
        binding.textCurrency.text = data?.country?.currency ?: unknownText
        binding.textBank.text = data?.bank?.name ?: unknownText
        binding.textUrl.text = data?.bank?.url ?: unknownText
        binding.textBankPhone.text = data?.bank?.phone ?: unknownText

        val roomData = RoomArticles(
            bin = bin,
            scheme = data?.scheme ?: unknownText,
            type = data?.type ?: unknownText,
            brand = data?.brand ?: unknownText,
            country = data?.country?.name.plus(" / ${data?.country?.alpha2}")
                .plus(" ${data?.country?.emoji}"),
            currency = data?.country?.currency ?: unknownText,
            bank = data?.bank?.name ?: unknownText,
            urlBank = data?.bank?.url ?: unknownText,
            phoneBank = data?.bank?.phone ?: unknownText,
            prepaid = data?.prepaid,
            lat = data?.country?.latitude ?: 0,
            lon = data?.country?.longitude ?: 0
        )

        lifecycleScope.launch {
            room.addBin(roomData)
        }

    }

    private fun setActions() {
        binding.binEdt.addTextChangedListener {
            checkIsEntered()
        }
        binding.btnSearch.setOnClickListener {
            sendRequest(binding.binEdt.text?.toString()?.replace("\\s".toRegex(), "") ?: "")
            binding.binEdt.text = null
        }
        binding.btnHistory.setOnClickListener {
            lifecycleScope.launch {
                Log.d("TAGRoomData", "setActions:${room.getBins()}")
            }
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
        currentBin = bin
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
