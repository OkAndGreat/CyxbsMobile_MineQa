package com.mredrock.cyxbs.mine.page.sign.fragment

import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentGoodsDetailBinding
import com.mredrock.cyxbs.mine.page.sign.adapter.GoodsDetailPicAdapter
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment

/**
 * Author by OkAndGreat，Date on 2021/8/1.
 * 商品详情fragment
 * 根据商品是虚拟商品还是实体商品页面显示不同
 */
class GoodsDetailFragment :
    BaseDataBindingFragment<MineFragmentGoodsDetailBinding>(R.layout.mine_fragment_goods_detail) {
    override fun initView() {
        mBinding.mineVp2GoodsPic.adapter = GoodsDetailPicAdapter()
    }

    override fun initData() {

    }

    override fun initOther() {

    }

}