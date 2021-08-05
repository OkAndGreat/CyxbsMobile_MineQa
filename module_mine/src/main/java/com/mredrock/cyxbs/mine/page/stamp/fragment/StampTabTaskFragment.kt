package com.mredrock.cyxbs.mine.page.stamp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.ui.BaseFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.adapter.StampCenterTaskAdapter
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampTaskViewModel
import com.mredrock.cyxbs.mine.util.ui.StampCenterTitleGoodsAdapter

class StampTabTaskFragment: BaseFragment() {

    private val viewModel:StampTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.mine_fragment_stamp_tab, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.mine_stamp_tab_rv)
        val adapter = StampCenterTaskAdapter(viewModel,this@StampTabTaskFragment)
        val layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        viewModel.getTodayTask()
        viewModel.getMoreTask()
        return view
    }
}