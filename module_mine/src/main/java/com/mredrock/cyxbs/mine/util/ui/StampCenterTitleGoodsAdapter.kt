package com.mredrock.cyxbs.mine.util.ui

import androidx.lifecycle.LifecycleOwner
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineListItemProductBinding
import com.mredrock.cyxbs.mine.databinding.MineListItemStampGoodEmptyBinding
import com.mredrock.cyxbs.mine.databinding.MineListItemStampGoodTitleBinding
import com.mredrock.cyxbs.mine.network.model.Goods
import com.mredrock.cyxbs.mine.page.stamp.StampCenterViewModel

class StampCenterTitleGoodsAdapter(private val viewModel: StampCenterViewModel,lifecycleOwner: LifecycleOwner): BaseDataBindingTitleAdapter<
        Goods,
        Goods,
        MineListItemProductBinding,
        MineListItemProductBinding,
        MineListItemStampGoodTitleBinding>(
    R.layout.mine_list_item_product,
    R.layout.mine_list_item_product,
    viewModel.goods,
    viewModel.goods2,
    R.layout.mine_list_item_stamp_good_title,
    lifecycleOwner,
    "邮货",
    "装扮"
        ) {


    override fun onBindItem1(mBinding: MineListItemProductBinding?, item: Goods, position: Int) {
        mBinding?.goods = item
        mBinding?.viewModel = viewModel

    }

    override fun onBindItem2(mBinding: MineListItemProductBinding?, item: Goods, position: Int) {
        mBinding?.goods = item
        mBinding?.viewModel = viewModel
    }

    override fun onBindTitleItem(
        mBinding: MineListItemStampGoodTitleBinding?,
        position: Int,
        title: String
    ) {
        mBinding?.string = title
    }
}