package com.mredrock.cyxbs.mine.page.stamp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampeBinding
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampGoodsDetailFragment
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampDetailFragment
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampCenterFragment
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampExchangeFragment
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampCenterViewModel
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampChangeViewModel

//邮票中心界面
class StampCenterActivity : BaseActivity() {

    private var mBinding: MineActivityStampeBinding? = null
    private val stampViewModel: StampCenterViewModel by viewModels()
    private val stampChangeViewModel: StampChangeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.mine_activity_stampe)
        initView()
    }

    private fun initView() {
        toFragmentForAnim(StampCenterFragment()).commit()

        stampViewModel.toGoodPager.observe(this, Observer {
            val goodsDetailFragment = StampGoodsDetailFragment()
            goodsDetailFragment.arguments
            toFragmentForAnim((goodsDetailFragment).apply {
                val bundle = Bundle()
                stampViewModel.userAccount.value?.let{
                    bundle.putInt("account",it)
                }
                bundle.putInt("id", it)
                this.arguments = bundle
            }).addToBackStack(null).commit()
        })

        //对跳转到明细界面进行监听
        stampViewModel.toDetailPager.observe(this, Observer {
            toFragmentForAnim(StampDetailFragment()).addToBackStack(null).commit()
        })
        //跳转到明细具体领取界面进行监听
        stampChangeViewModel.toExChangePager.observe(this, Observer {
            val exchangeFragment = StampExchangeFragment()
            exchangeFragment.arguments
            toFragmentForAnim((exchangeFragment).apply {
                val bundle = Bundle()
                bundle.putInt("args", it)
                this.arguments = bundle
            }).addToBackStack(null).commit()
        })
    }

    override fun onBackPressed() {
        //对Fragment栈进行判断,如果栈清空了才让返回上一层activity
        if (
            supportFragmentManager.backStackEntryCount > 0
        ) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    //对fragment跳转添加动画
    private fun toFragmentForAnim(fragment: Fragment): FragmentTransaction {
        return supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.mine_fragment_enter,
            R.anim.mine_fragment_exit,
            R.anim.mine_fragment_pop_enter,
            R.anim.mine_fragment_pop_exit
        ).replace(R.id.mine_stamp_center_fragment, fragment)
    }
}