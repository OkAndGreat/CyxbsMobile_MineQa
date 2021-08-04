package com.mredrock.cyxbs.mine.page.sign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.sign.adapter.StampDetailRvAdapter
import kotlinx.android.synthetic.main.mine_fragment_stamp_detail_item.*

/**
 * Author by OkAndGreat，Date on 2021/8/4.
 * 邮票明细页面viewpager的list fragment
 */
class StampDetailListFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mine_fragment_stamp_detail_item,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mine_rv_stamp_detail.layoutManager = LinearLayoutManager(activity)
        mine_rv_stamp_detail.adapter = StampDetailRvAdapter()
    }

}