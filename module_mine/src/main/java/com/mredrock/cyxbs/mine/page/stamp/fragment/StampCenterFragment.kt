package com.mredrock.cyxbs.mine.page.stamp.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.utils.extensions.onClick
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampCenterBinding
import com.mredrock.cyxbs.mine.page.sign.viewmodel.StampViewModel
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment
import com.mredrock.cyxbs.mine.util.ui.StampTabPageAdapter

class StampCenterFragment: BaseDataBindingFragment<MineFragmentStampCenterBinding>(R.layout.mine_fragment_stamp_center) {

    val viewModel: StampViewModel by activityViewModels()

    private var fragmentList: ArrayList<Fragment> = ArrayList()

    override fun initView() {
        fragmentList.add(StampTabFragment())
        fragmentList.add(StampTabTaskFragment())
        val stampTabPageAdapter = StampTabPageAdapter(this, fragmentList)
        mBinding.mineStampCenterTlVp.adapter = stampTabPageAdapter
        //设置tabLayout
        mBinding.mineStampCenterTl.let { tabLayout ->
            mBinding.mineStampCenterTlVp.let { viewPager ->
                TabLayoutMediator(
                    tabLayout,
                    viewPager)
                { tab, position ->
                    when (position) {
                        0 -> {
                            tab.text = "邮票小店"
                        }
                        1 -> {
                            tab.text = "邮票任务"
                        }
                    }
                }
            }.attach()
        }

        mBinding.mineStampCenterBackIv.onClick { viewModel.load() }
    }

    override fun initData() {

    }

    override fun initOther() {

    }

}