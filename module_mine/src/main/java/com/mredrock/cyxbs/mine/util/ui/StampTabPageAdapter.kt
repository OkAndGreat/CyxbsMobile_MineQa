package com.mredrock.cyxbs.mine.util.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class StampTabPageAdapter(
    fragmentActivity:FragmentActivity,
    val fragmentList:List<Fragment>
    ): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}