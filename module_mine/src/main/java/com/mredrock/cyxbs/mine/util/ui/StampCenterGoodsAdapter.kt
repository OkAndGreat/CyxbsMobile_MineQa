package com.mredrock.cyxbs.mine.util.ui

import androidx.lifecycle.LifecycleOwner
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineListItemProductBinding
import com.mredrock.cyxbs.mine.network.model.Goods
import com.mredrock.cyxbs.mine.page.sign.viewmodel.StampViewModel

class StampCenterGoodsAdapter(viewModel: StampViewModel,lifecycleOwner: LifecycleOwner):
    BaseDataBindingAdapter<Goods,MineListItemProductBinding>(
        R.layout.mine_list_item_product,
        viewModel.goods,
        lifecycleOwner
    ){

    override fun onBindItem(mBinding: MineListItemProductBinding?, item: Goods) {
        mBinding?.goods = item
    }
}