package com.mredrock.cyxbs.mine.page.sign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.sign.adapter.StampDetailVpAdapter
import kotlinx.android.synthetic.main.mine_fragment_stamp_detail.*

/**
 * Author by OkAndGreat，Date on 2021/8/4.
 * 邮票中心兑换详情页面的fragment
 */
class StampDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mine_fragment_stamp_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mine_vp_stamp_detail.adapter =
            activity?.let { StampDetailVpAdapter(it.supportFragmentManager) }

        mine_tl_stamp_detail.setupWithViewPager(mine_vp_stamp_detail)
        //添加自定义tab
        for (i in 0..mine_tl_stamp_detail.tabCount) {
            val tab = mine_tl_stamp_detail.getTabAt(i)
            tab?.customView = LayoutInflater.from(activity).inflate(R.layout.mine_tab_stamp_detail_item, null)
        }
    }

}