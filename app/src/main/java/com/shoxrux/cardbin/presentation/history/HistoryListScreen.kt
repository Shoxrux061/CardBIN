package com.shoxrux.cardbin.presentation.history

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shoxrux.cardbin.R
import com.shoxrux.cardbin.data.room.RoomDao
import com.shoxrux.cardbin.databinding.ScreenHistoryListBinding
import com.shoxrux.cardbin.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HistoryListScreen : BaseFragment(R.layout.screen_history_list) {

    @Inject
    lateinit var room: RoomDao
    private val binding by viewBinding(ScreenHistoryListBinding::bind)
    private val adapter by lazy { HistoryAdapter() }


    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
    }

    private fun setAdapter() {
        binding.root.adapter = adapter
        binding.root.layoutManager = LinearLayoutManager(context).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        lifecycleScope.launch {
            adapter.setData(room.getBins())
        }
    }
}
