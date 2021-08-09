package com.mredrock.cyxbs.mine.page.stamp.adapter

import androidx.lifecycle.LifecycleOwner
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineListItemStampGetDetailBinding
import com.mredrock.cyxbs.mine.network.model.stamp.GetChangeDetail
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampChangeViewModel
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingAdapter

class StampGetChangeAdapter(
    private val viewModel: StampChangeViewModel,
    lifecycleOwner: LifecycleOwner
) : BaseDataBindingAdapter<
        GetChangeDetail,
        MineListItemStampGetDetailBinding>(
    R.layout.mine_list_item_stamp_get_detail,
    viewModel.getChangeDetails,
    lifecycleOwner
) {

    override fun onBindItem(
        mBinding: MineListItemStampGetDetailBinding?,
        item: GetChangeDetail,
        position: Int
    ) {
        mBinding?.viewModel = viewModel
        mBinding?.getChange = item
    }
}