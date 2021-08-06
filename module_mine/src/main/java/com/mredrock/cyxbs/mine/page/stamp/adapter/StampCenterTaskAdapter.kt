package com.mredrock.cyxbs.mine.page.stamp.adapter

import androidx.lifecycle.LifecycleOwner
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineListItemStampTaskBinding
import com.mredrock.cyxbs.mine.databinding.MineListItemStampTitleBinding
import com.mredrock.cyxbs.mine.network.model.stamp.StampTask
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampTaskViewModel
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingTitleAdapter

class StampCenterTaskAdapter(private val viewModel: StampTaskViewModel,
                             lifecycleOwner: LifecycleOwner,
                             scheduleLayoutAnimation: Unit):BaseDataBindingTitleAdapter<
        StampTask,
        StampTask,
        MineListItemStampTaskBinding,
        MineListItemStampTaskBinding,
        MineListItemStampTitleBinding
        >(
        R.layout.mine_list_item_stamp_task,
        R.layout.mine_list_item_stamp_task,
        viewModel.todayTasks,
        viewModel.moreTasks,
        R.layout.mine_list_item_stamp_title,
        lifecycleOwner,
        "更多任务") {
        override fun onBindTitleItem(
                mBinding: MineListItemStampTitleBinding?,
                position: Int,
                title1: String
        ) {
                mBinding?.string = title1
        }

        override fun onBindItem1(
            mBinding: MineListItemStampTaskBinding?,
            item: StampTask,
            position: Int
        ) {
                //进度条自定义view设置值
                mBinding?.mineListItemStampPl?.post { mBinding.mineListItemStampPl.setCurCount(item.doneAmount) }
                mBinding?.mineListItemStampPl?.setMaxCount(item.totalAmount)
                mBinding?.task = item
        }

        override fun onBindItem2(
            mBinding: MineListItemStampTaskBinding?,
            item: StampTask,
            position: Int
        ) {
                //进度条自定义view设置值
                mBinding?.mineListItemStampPl?.post { mBinding.mineListItemStampPl.setCurCount(item.doneAmount) }
                mBinding?.mineListItemStampPl?.setMaxCount(item.totalAmount)
                mBinding?.task = item
        }

}