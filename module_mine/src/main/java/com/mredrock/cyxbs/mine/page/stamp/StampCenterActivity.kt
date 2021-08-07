package com.mredrock.cyxbs.mine.page.stamp

import android.graphics.Color
import android.os.Bundle
import android.widget.TableLayout
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.ui.BaseViewModelActivity
import com.mredrock.cyxbs.common.utils.extensions.onClick
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityStampeBinding
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampGoodsDetailFragment
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampDetailFragment
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampCenterFragment
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampCenterViewModel

//邮票中心界面
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
            val goodsDetailFragment = StampGoodsDetailFragment()
            goodsDetailFragment.arguments
            toFragmentForAnim((StampGoodsDetailFragment()).apply {
                val bundle = Bundle()
                bundle.putString("args",it)
                this.arguments = bundle
            }).addToBackStack(null).commit()
        })

        //对跳转到明细界面进行监听
        stampViewModel.toDetailPager.observe(this, Observer {
            toFragmentForAnim(StampDetailFragment()).addToBackStack(null).commit()
        })
    }

    override fun onBackPressed() {
        //对Fragment栈进行判断,如果栈清空了才让返回上一层activity
        if (
            supportFragmentManager.backStackEntryCount > 0
        ){
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }
    }

    //对fragment跳转添加动画
    private fun toFragmentForAnim(fragment:Fragment):FragmentTransaction{
            return supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.mine_fragment_enter,
                R.anim.mine_fragment_exit,
                R.anim.mine_fragment_pop_enter,
                R.anim.mine_fragment_pop_exit
            ).replace(R.id.mine_stamp_center_fragment,fragment)
    }
}