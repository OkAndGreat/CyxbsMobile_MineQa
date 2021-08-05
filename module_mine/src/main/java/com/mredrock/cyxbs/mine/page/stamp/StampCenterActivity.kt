package com.mredrock.cyxbs.mine.page.stamp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampeBinding
import com.mredrock.cyxbs.mine.page.sign.fragment.GoodsDetailFragment
import com.mredrock.cyxbs.mine.page.sign.fragment.StampDetailFragment
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampCenterFragment

class StampCenterActivity : BaseActivity() {


    private var mBinding: MineActivityStampeBinding? = null
    private val stampViewModel: StampCenterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.mine_activity_stampe);
        initView()
    }

    private fun initView() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.mine_stamp_center_fragment, StampCenterFragment()).commit()

        stampViewModel.toGoodPager.observe(this, Observer<String> {
            val goodsDetailFragment = GoodsDetailFragment()
            goodsDetailFragment.arguments
            supportFragmentManager.beginTransaction()
                .replace(R.id.mine_stamp_center_fragment, GoodsDetailFragment().apply {
                    val bundle = Bundle()
                    bundle.putString("args", it)
                    this.arguments = bundle
                }).commit()
        })
        stampViewModel.toDetailPager.observe(this, Observer {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mine_stamp_center_fragment, StampDetailFragment()).commit()
        })
    }

}