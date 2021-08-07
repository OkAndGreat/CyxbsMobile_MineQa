package com.mredrock.cyxbs.mine.page.stamp.adapter

import androidx.lifecycle.LifecycleOwner
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineListItemStampDecorationBinding
import com.mredrock.cyxbs.mine.databinding.MineListItemStampGoodBinding
import com.mredrock.cyxbs.mine.databinding.MineListItemStampGoodTitleBinding
import com.mredrock.cyxbs.mine.network.model.stamp.Decoration
import com.mredrock.cyxbs.mine.network.model.stamp.StampGood
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampCenterViewModel
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingTitleAdapter

class StampCenterTitleGoodsAdapter(
    private val viewModel: StampCenterViewModel,
    lifecycleOwner: LifecycleOwner,
    scheduleLayoutAnimation: () -> Unit
): BaseDataBindingTitleAdapter<
        Decoration,
        StampGood,
        MineListItemStampDecorationBinding,
        MineListItemStampGoodBinding,
        MineListItemStampGoodTitleBinding>(
    R.layout.mine_list_item_stamp_decoration,
    R.layout.mine_list_item_stamp_good,
    viewModel.decorations,
    viewModel.goods,
    R.layout.mine_list_item_stamp_good_title,
    lifecycleOwner,
    "邮货",
    "装扮",
    scheduleLayoutAnimation
        ) {

    //绑定虚拟商品
    override fun onBindItem1(
        mBinding: MineListItemStampDecorationBinding?,
        item: Decoration,
        position: Int
    ) {

        mBinding?.decoration = item
        mBinding?.viewModel = viewModel
    }
    //绑定实体商品
    override fun onBindItem2(
        mBinding: MineListItemStampGoodBinding?,
        item: StampGood,
        position: Int
    ) {
        mBinding?.stampGood = item
        mBinding?.viewModel = viewModel
    }
    //绑定title
    override fun onBindTitleItem(
        mBinding: MineListItemStampGoodTitleBinding?,
        position: Int,
        title: String
    ) {
        mBinding?.string = title
        mBinding?.viewModel = viewModel
    }






}