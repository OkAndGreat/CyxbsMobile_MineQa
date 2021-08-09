package com.mredrock.cyxbs.mine.page.stamp.adapter

import androidx.lifecycle.LifecycleOwner
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineListItemStampDetailBinding
import com.mredrock.cyxbs.mine.network.model.stamp.ExChangeDetail
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampChangeViewModel
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingAdapter

class StampExChangeAdapter(
    private val viewModel: StampChangeViewModel,
    lifecycleOwner: LifecycleOwner
) : BaseDataBindingAdapter<
        ExChangeDetail,
        MineListItemStampDetailBinding>(
    R.layout.mine_list_item_stamp_detail,
    viewModel.exChangeDetails,
    lifecycleOwner
) {
    override fun onBindItem(
        mBinding: MineListItemStampDetailBinding?,
        item: ExChangeDetail,
        position: Int
    ) {
        mBinding?.viewModel = viewModel
        mBinding?.exChange = item
    }
}