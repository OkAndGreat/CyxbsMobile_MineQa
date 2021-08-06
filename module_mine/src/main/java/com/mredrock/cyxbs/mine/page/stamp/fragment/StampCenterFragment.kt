package com.mredrock.cyxbs.mine.page.stamp.fragment

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.inflate
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.utils.extensions.onClick
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineActivityFindPasswordBinding.inflate
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampCenterBinding
import com.mredrock.cyxbs.mine.page.stamp.StampCenterViewModel
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment
import com.mredrock.cyxbs.mine.util.ui.StampTabPageAdapter
import org.w3c.dom.Text
import kotlin.math.abs



class StampCenterFragment: BaseDataBindingFragment<MineFragmentStampCenterBinding>(R.layout.mine_fragment_stamp_center){

    val viewModel: StampCenterViewModel by activityViewModels()

    private var fragmentList: ArrayList<Fragment> = ArrayList()

    override fun initView() {
        if(fragmentList.size == 0){
            fragmentList.add(StampTabFragment())
            fragmentList.add(StampTabTaskFragment())
        }

        val stampTabPageAdapter = StampTabPageAdapter(this, fragmentList)
        mBinding.mineStampCenterTlVp.adapter = stampTabPageAdapter
        //设置tabLayout
        mBinding.mineStampCenterTl.let { tabLayout ->
            val customView = tabLayout.newTab().setCustomView(R.layout.mine_item_tablayout_stamp_hint).customView
            val hintIv: ImageView? =  customView?.findViewById(R.id.mine_item_tl_hint_iv)
            val hintTv: TextView? =  customView?.findViewById(R.id.mine_item_tl_hint_tv)
            TabLayoutMediator(
                tabLayout,
                mBinding.mineStampCenterTlVp)
            { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "邮票小店"
                    }
                    1 -> {
                        tab.customView = customView
                    }
                }
            }.attach()
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.position == 1) {
                        hintIv?.visibility = GONE
                        context?.let { ContextCompat.getColor(it,R.color.common_level_two_font_color) }?.let {
                            hintTv?.setTextColor(it)
                    }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    if (tab?.position == 1) {
                        context?.let { ContextCompat.getColor(it,R.color.common_alpha_level_two_font_color) }?.let {
                        hintTv?.setTextColor(it)
                    }
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    context?.let { ContextCompat.getColor(it,R.color.common_alpha_level_two_font_color) }?.let {
                        hintTv?.setTextColor(it)
                    }
                }

            })
        }
    }

    override fun initData() {
        mBinding.mineStampCenterAbl.apply {
            addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                Log.d(TAG,"滑动 $verticalOffset 滑动最大值${mBinding.mineStampCenterFl.height}")
                mBinding.mineStampCenterFl.apply {
                    changeAnim(this,this.height+verticalOffset,this.height)

                }
            })
        }
    }



    override fun initOther() {
        mBinding.mineStampCenterFl.setOnClickListener { viewModel.onClickForToDetailPager() }
        mBinding.mineStampCenterBackIv.onClick { viewModel.load() }
    }

    private fun changeAnim(view: View,offset: Int,maxHeight: Int) {
        val ratio = (offset.toFloat() / maxHeight)
        view.scaleX = ratio
        view.scaleY = ratio
        view.alpha = ratio
    }

}