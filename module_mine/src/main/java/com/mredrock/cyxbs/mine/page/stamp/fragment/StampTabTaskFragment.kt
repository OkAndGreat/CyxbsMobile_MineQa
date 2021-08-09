package com.mredrock.cyxbs.mine.page.stamp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.ui.BaseFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.adapter.StampCenterTaskAdapter
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampTaskViewModel

/**
 * 任务列表
 */
class StampTabTaskFragment : BaseFragment() {

    private val viewModel: StampTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.mine_fragment_stamp_tab_tasks, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.mine_stamp_tab_rv_tasks)
        //rvAdapter
        val adapter = StampCenterTaskAdapter(
            viewModel, this@StampTabTaskFragment
        ) { recyclerView.scheduleLayoutAnimation() }
        val layoutManager = LinearLayoutManager(context)
        LayoutAnimationController.AnimationParameters()
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        loadData()
        return view
    }

    private fun loadData() {
        viewModel.getTodayTask()
        viewModel.getMoreTask()
    }
}