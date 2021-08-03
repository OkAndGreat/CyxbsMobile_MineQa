package com.mredrock.cyxbs.mine.page.sign.fragment

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.utils.extensions.onClick
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampCenterBinding
import com.mredrock.cyxbs.mine.page.sign.viewmodel.StampViewModel
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment
import com.mredrock.cyxbs.mine.util.ui.StampCenterGoodsAdapter

class StampCenterFragment:BaseDataBindingFragment<MineFragmentStampCenterBinding>(R.layout.mine_fragment_stamp_center) {

    val viewModel: StampViewModel by activityViewModels()

    override fun initView() {
        viewModel.attach(this)
//        mBinding.mineStampCenterGoodsRv.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = StampCenterGoodsAdapter(viewModel,this@StampCenterFragment)
//        }



    }

    override fun initData() {
        mBinding.mineStampCenterTitle.onClick {
            viewModel.load()

        }
    }

    override fun initOther() {

    }
}