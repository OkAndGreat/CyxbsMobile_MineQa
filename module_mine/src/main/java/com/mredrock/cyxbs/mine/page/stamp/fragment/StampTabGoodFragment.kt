package com.mredrock.cyxbs.mine.page.stamp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.GridLayoutAnimationController
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.mredrock.cyxbs.common.ui.BaseFragment
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.adapter.StampCenterTitleGoodsAdapter
import com.mredrock.cyxbs.mine.page.stamp.customview.progressview.GridRecyclerView
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampCenterViewModel

/**
 * 首页物品rv fragment
 */
class StampTabGoodFragment : BaseFragment() {

    private val viewModel: StampCenterViewModel by activityViewModels()
    private var recyclerView: GridRecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.mine_fragment_stamp_tab_goods, container, false)

        recyclerView = view.findViewById(R.id.mine_stamp_tab_rv_goods)
        //设置rvAdapter
        val mAdapter = StampCenterTitleGoodsAdapter(
            viewModel, this@StampTabGoodFragment
        ) { recyclerView?.scheduleLayoutAnimation() }

        val mLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        mLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (mAdapter.getItemViewType(position)) {
                    mAdapter.TITLE_ONE -> 2
                    mAdapter.TITLE_TWO -> 2
                    else -> 1
                }
            }

        }
        recyclerView?.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
            GridLayoutAnimationController.AnimationParameters()
        }

        viewModel.loadCenterGoods()

        return view
    }

    override fun onDestroyView() {
        //这里清空是为了让rv销毁的时候让adapter也同时触发销毁
        recyclerView?.adapter = null
        super.onDestroyView()
    }
}