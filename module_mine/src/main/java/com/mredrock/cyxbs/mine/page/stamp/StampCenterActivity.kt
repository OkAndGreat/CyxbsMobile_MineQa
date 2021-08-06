package com.mredrock.cyxbs.mine.page.stamp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampeBinding
import com.mredrock.cyxbs.mine.page.stamp.fragment.GoodsDetailFragment
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampDetailFragment
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
        toFragmentForAnim(StampCenterFragment()).commit()

        stampViewModel.toGoodPager.observe(this, Observer<String> {
            val goodsDetailFragment = GoodsDetailFragment()
            goodsDetailFragment.arguments
            toFragmentForAnim((GoodsDetailFragment()).apply {
                val bundle = Bundle()
                bundle.putString("args",it)
                this.arguments = bundle
            }).addToBackStack(null).commit()
        })
        stampViewModel.toDetailPager.observe(this, Observer {
            toFragmentForAnim(StampDetailFragment()).addToBackStack(null).commit()
        })
    }

    override fun onBackPressed() {
        if (
            supportFragmentManager.backStackEntryCount > 0
        ){
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }
    }

    private fun toFragmentForAnim(fragment:Fragment):FragmentTransaction{
            return supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.mine_fragment_enter,
                R.anim.mine_fragment_exit,
                R.anim.mine_fragment_pop_enter,
                R.anim.mine_fragment_pop_exit
            ).replace(R.id.mine_stamp_center_fragment,fragment)
    }
}