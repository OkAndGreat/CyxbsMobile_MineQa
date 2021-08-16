package com.mredrock.cyxbs.mine.page.stamp.fragment

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampGetDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.adapter.EndlessRecyclerOnScrollListener
import com.mredrock.cyxbs.mine.page.stamp.adapter.StampGetChangeAdapter
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampChangeViewModel
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment

class StampGetDetailListFragment :
    BaseDataBindingFragment<MineFragmentStampGetDetailBinding>(R.layout.mine_fragment_stamp_get_detail) {

    private val viewModel: StampChangeViewModel by activityViewModels()

    override fun initView() {
        val linearLayoutManager = LinearLayoutManager(activity)

        mBinding.mineRvStampGetDetail.addOnScrollListener(object : EndlessRecyclerOnScrollListener(linearLayoutManager,10){
            override fun onLoadMore(currentPage: Int) {
                viewModel.loadGetChangeDetails(currentPage)
            }
        })
        mBinding.mineRvStampGetDetail.layoutManager = linearLayoutManager
        mBinding.mineRvStampGetDetail.adapter = StampGetChangeAdapter(viewModel, this)
        viewModel.loadGetChangeDetails(1)
    }

    override fun initData() {

    }

    override fun initOther() {

    }
}