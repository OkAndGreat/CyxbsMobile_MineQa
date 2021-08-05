package com.mredrock.cyxbs.mine.util.ui

import androidx.lifecycle.LifecycleOwner
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineListItemProductBinding
import com.mredrock.cyxbs.mine.network.model.Goods
import com.mredrock.cyxbs.mine.page.sign.viewmodel.StampViewModel
import com.mredrock.cyxbs.mine.page.stamp.StampCenterViewModel

class StampCenterGoodsAdapter(private val viewModel: StampCenterViewModel, lifecycleOwner: LifecycleOwner):
    BaseDataBindingAdapter<Goods,MineListItemProductBinding>(
        R.layout.mine_list_item_product,
        viewModel.goods,
        lifecycleOwner
    ){

    override fun onBindItem(mBinding: MineListItemProductBinding?, item: Goods, position: Int) {
        mBinding?.goods = item
        mBinding?.viewModel = viewModel
    }
}