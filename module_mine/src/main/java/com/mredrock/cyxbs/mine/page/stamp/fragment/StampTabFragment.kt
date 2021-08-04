package com.mredrock.cyxbs.mine.page.stamp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.ui.BaseFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.StampCenterViewModel
import com.mredrock.cyxbs.mine.util.ui.StampCenterGoodsAdapter
import com.mredrock.cyxbs.mine.util.ui.StampCenterTitleGoodsAdapter
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup as SpanSizeLookup

class StampTabFragment:BaseFragment() {

    val viewModel:StampCenterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.mine_fragment_stamp_tab, container, false)

        val recyclerView:RecyclerView = view.findViewById(R.id.mine_stamp_tab_rv)
        val adapter = StampCenterTitleGoodsAdapter(viewModel,this@StampTabFragment)
        val layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        layoutManager.spanSizeLookup = object:SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return when(adapter.getItemViewType(position)){
                    adapter.TITLE_ONE -> 2
                    adapter.TITLE_TWO -> 2
                    else -> 1
                }
            }

        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

            viewModel.load()
        return view
    }
}