package com.mredrock.cyxbs.mine.page.stamp.fragment

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampGetDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.adapter.StampGetChangeAdapter
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampChangeViewModel
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment

class StampGetDetailListFragment :
    BaseDataBindingFragment<MineFragmentStampGetDetailBinding>(R.layout.mine_fragment_stamp_get_detail) {

    private val viewModel: StampChangeViewModel by activityViewModels()

    override fun initView() {
        mBinding.mineRvStampGetDetail.layoutManager = LinearLayoutManager(activity)
        mBinding.mineRvStampGetDetail.adapter = StampGetChangeAdapter(viewModel, this)
        viewModel.loadGetChangeDetails()
    }

    override fun initData() {

    }

    override fun initOther() {

    }
}