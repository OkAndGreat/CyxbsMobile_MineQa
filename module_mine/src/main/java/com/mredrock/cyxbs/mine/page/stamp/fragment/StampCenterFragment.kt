package com.mredrock.cyxbs.mine.page.stamp.fragment

import android.content.Context
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mredrock.cyxbs.common.utils.extensions.onClick
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampCenterBinding
import com.mredrock.cyxbs.mine.page.stamp.StampCenterViewModel
import com.mredrock.cyxbs.mine.util.getDateDay
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment
import com.mredrock.cyxbs.mine.util.ui.StampTabPageAdapter


class StampCenterFragment :
    BaseDataBindingFragment<MineFragmentStampCenterBinding>(R.layout.mine_fragment_stamp_center) {

    //进入这个fragment时的时间戳
    private var mCurTimeStamp = 0L

    //是否应该展示小蓝点
    private var mShouldShowBlueDot = true


    companion object {
        //一天的时间戳
        const val ONE_DAY_TIMES_STAMP = 86400000
    }


    val viewModel: StampCenterViewModel by activityViewModels()

    private var fragmentList: ArrayList<Fragment> = ArrayList()

    override fun initView() {
        //进来fragment后就对当前时间戳赋值
        mCurTimeStamp = System.currentTimeMillis()
        //拿到上一次的时间戳 如果是第一次进入 则返回0
        val prefs = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val mLastTimeStamp = prefs?.getLong("TimeStamp", 0L)
        //储存当前时间戳
        val editor = activity?.getSharedPreferences("data", Context.MODE_PRIVATE)?.edit()
        editor?.putLong("TimeStamp", mCurTimeStamp)
        editor?.apply()

        if (mLastTimeStamp == 0L) {
            //说明是第一次进入，初始化了sp
            mShouldShowBlueDot = true
        } else {
            if (mLastTimeStamp != null) {
                if ((mCurTimeStamp.getDateDay() - mLastTimeStamp.getDateDay()) == 0) {
                    Log.d(TAG, "当前是在同一天")
                    //说明目前在同一天
                    mShouldShowBlueDot = false
                }

            }
        }

        if (fragmentList.size == 0) {
            fragmentList.add(StampTabFragment())
            fragmentList.add(StampTabTaskFragment())
        }

        val stampTabPageAdapter = StampTabPageAdapter(this, fragmentList)
        mBinding.mineStampCenterTlVp.adapter = stampTabPageAdapter
        //设置tabLayout
        mBinding.mineStampCenterTl.let { tabLayout ->
            val customView =
                tabLayout.newTab().setCustomView(R.layout.mine_item_tablayout_stamp_hint).customView
            val hintIv: ImageView? = customView?.findViewById(R.id.mine_item_tl_hint_iv)
            val hintTv: TextView? = customView?.findViewById(R.id.mine_item_tl_hint_tv)
            TabLayoutMediator(
                tabLayout,
                mBinding.mineStampCenterTlVp
            )
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

            if (!mShouldShowBlueDot) {
                hintIv?.visibility = GONE
            }

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.position == 1) {
                        context?.let {
                            ContextCompat.getColor(
                                it,
                                R.color.common_level_two_font_color
                            )
                        }?.let {
                            hintTv?.setTextColor(it)
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    if (tab?.position == 1) {
                        context?.let {
                            ContextCompat.getColor(
                                it,
                                R.color.common_alpha_level_two_font_color
                            )
                        }?.let {
                            hintTv?.setTextColor(it)
                        }
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    context?.let {
                        ContextCompat.getColor(
                            it,
                            R.color.common_alpha_level_two_font_color
                        )
                    }?.let {
                        hintTv?.setTextColor(it)
                    }
                }

            })
        }
    }

    override fun initData() {
        mBinding.mineStampCenterAbl.apply {
            addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
                Log.d(TAG, "滑动 $verticalOffset 滑动最大值${mBinding.mineStampCenterFl.height}")
                mBinding.mineStampCenterFl.apply {
                    changeAnim(this, this.height + verticalOffset, this.height)

                }
            })
        }
    }


    override fun initOther() {
        mBinding.mineStampCenterFl.setOnClickListener { viewModel.onClickForToDetailPager() }
        mBinding.mineStampCenterBackIv.onClick { viewModel.load() }
    }

    private fun changeAnim(view: View, offset: Int, maxHeight: Int) {
        val ratio = (offset.toFloat() / maxHeight)
        view.scaleX = ratio
        view.scaleY = ratio
        view.alpha = ratio
    }

}