package com.mredrock.cyxbs.mine.page.stamp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mredrock.cyxbs.mine.page.sign.fragment.StampDetailListFragment

/**
 * Author by OkAndGreat，Date on 2021/8/6.
 * 邮票明细页面Vp2的adapter
 */
class StampDetailVp2Adapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = StampDetailListFragment()

}