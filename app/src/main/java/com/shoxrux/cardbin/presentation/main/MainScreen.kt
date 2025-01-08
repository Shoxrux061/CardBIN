package com.shoxrux.cardbin.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.shoxrux.cardbin.R
import com.shoxrux.cardbin.presentation.base.BaseFragment

class MainScreen : BaseFragment(R.layout.screen_main) {

    private val viewModel: BinViewModel by viewModels()

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    private fun sendRequest(bin: Int) {
        viewModel.getCardBin(bin)
    }

}
