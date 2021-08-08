package com.mredrock.cyxbs.mine.page.stamp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentExchangeDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampChangeViewModel
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment

/**
 * Author by OkAndGreat，Date on 2021/8/4.
 * 兑换详情fragment
 * 根据商品是虚拟商品还是实体商品页面显示不同
 * 虚拟商品默认为灰色状态
 * xml文件写的是未领取状态下的，因此需要根据数据是虚拟商品还是实体商品来代码设置
 * mine_cardView_stamp_exchange_detail 的color为
 * #979797
 * 和mine_iv_stamp_exchange_detail的background为
 * mine_vector_stamp_exchange_detail_icon_collected.xml
 */
class StampExchangeFragment() :
    BaseDataBindingFragment<MineFragmentExchangeDetailBinding>(R.layout.mine_fragment_exchange_detail) {

    private val viewModel:StampChangeViewModel by activityViewModels()

    override fun initView() {
        val getId = arguments?.get("args") as Int
        mBinding.exChangeDetail = viewModel.getOneExChangeById(getId)
    }

    override fun initData() {

    }

    override fun initOther() {

    }


}