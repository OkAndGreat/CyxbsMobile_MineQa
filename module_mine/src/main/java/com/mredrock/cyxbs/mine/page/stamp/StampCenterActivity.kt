package com.mredrock.cyxbs.mine.page.stamp

import android.graphics.Color
import android.os.Bundle
import android.widget.TableLayout
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.common.utils.extensions.onClick
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampeBinding
import com.mredrock.cyxbs.mine.page.sign.fragment.GoodsDetailFragment
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampCenterFragment

class StampCenterActivity: BaseActivity() {

    private var mBinding:MineActivityStampeBinding? = null
    private val stampViewModel: StampCenterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.mine_activity_stampe);
        initView()
    }

    private fun initView() {

        supportFragmentManager.beginTransaction().replace(R.id.mine_stamp_center_fragment, StampCenterFragment()).commit()

        stampViewModel.toGoodPager.observe(this, Observer<String> {
            val goodsDetailFragment = GoodsDetailFragment()
            goodsDetailFragment.arguments
            supportFragmentManager.beginTransaction().replace(R.id.mine_stamp_center_fragment, GoodsDetailFragment().apply {
                val bundle = Bundle()
                bundle.putString("args",it)
                this.arguments = bundle
            }).commit()
        })




    }






}