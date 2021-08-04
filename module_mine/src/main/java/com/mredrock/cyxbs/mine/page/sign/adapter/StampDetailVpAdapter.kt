package com.mredrock.cyxbs.mine.page.sign.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mredrock.cyxbs.mine.page.sign.fragment.StampDetailListFragment

/**
 * Author by OkAndGreat，Date on 2021/8/4.
 * 邮票明细页面ViewPager的adapter
 */
class StampDetailVpAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment = StampDetailListFragment()

}