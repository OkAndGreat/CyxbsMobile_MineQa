package com.mredrock.cyxbs.mine.page.stamp

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampeBinding
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampTabFragment
import com.mredrock.cyxbs.mine.util.ui.StampTabPageAdapter

class StampCenterActivity: BaseViewModelActivity<StampCenterViewModel>() {

    private var mBinding:MineActivityStampeBinding? = null

    private var fragmentList: ArrayList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.mine_activity_stampe);
        initView()
    }

    private fun initView() {
        fragmentList.add(StampTabFragment())
        fragmentList.add(StampTabFragment())
        mBinding?.mineStampCenterVp?.adapter = StampTabPageAdapter(this,fragmentList)
        if ( mBinding.mineStampCenterTl != null && mBinding.mineStampCenterVp )
        TabLayoutMediator(mBinding?.mineStampCenterTl,
            mBinding?.mineStampCenterVp,
            TabLayoutMediator.TabConfigurationStrategy(){

            })
    }






}