package com.mredrock.cyxbs.mine.page.stamp.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentStampDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.adapter.StampDetailVp2Adapter
import com.mredrock.cyxbs.mine.util.ProgressUtils
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment
import kotlinx.android.synthetic.main.mine_fragment_stamp_detail.*

/**
 * Author by OkAndGreat，Date on 2021/8/4.
 * 邮票中心兑换详情页面的fragment
 */
class StampDetailFragment :
    BaseDataBindingFragment<MineFragmentStampDetailBinding>(R.layout.mine_fragment_stamp_detail) {
    companion object {
        //滑动tab时tab改变的值
        const val TO_CHANGE_SIZE = 2
        const val TO_CHANGE_ALPHA = 0.2

        //是否处于深色模式或者浅色模式的判断值
        const val DARK_MODE = 0x21
        const val LIGHT_MODE = 0x11

        //浅色模式下俩个tab所对应的颜色Int值
        const val NORMAL_TEXT_COLOR_LIGHT = -15388325
        const val SELECTED_TEXT_COLOR_LIGHT = -10521116

        //深色模式下俩个tab所对应的颜色Int值
        const val NORMAL_TEXT_COLOR_DARK = -856624910
        const val SELECTED_TEXT_COLOR_DARK = -8941825
    }


    private var NORMAL_TEXT_COLOR = 0
    private var SELECTED_TEXT_COLOR = 0

    private var mCurPosition = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding.mineVp2StampDetail.adapter = activity?.let { StampDetailVp2Adapter(it) }

        initListener()
        initVp2()
    }

    override fun initView() {
    }

    private fun initVp2() {

        //根据是否是深色模式进行适配
        if (requireActivity().applicationContext.resources.configuration.uiMode == DARK_MODE) {
            NORMAL_TEXT_COLOR = NORMAL_TEXT_COLOR_DARK
            SELECTED_TEXT_COLOR = SELECTED_TEXT_COLOR_DARK

        }
        if (requireActivity().applicationContext.resources.configuration.uiMode == LIGHT_MODE) {
            NORMAL_TEXT_COLOR = NORMAL_TEXT_COLOR_LIGHT
            SELECTED_TEXT_COLOR = SELECTED_TEXT_COLOR_LIGHT
        }
        //通过进入邮票明细页面默认同时加载兑换记录页面和获取记录页面
        //vp2默认懒加载，这会导致黑夜模式下从兑换记录页面切换到获取记录页面时获取记录获取数据导致闪烁
        mine_vp2_stamp_detail.offscreenPageLimit = 3
        //监听ViewPager2来改变tab的颜色和大小
        mine_vp2_stamp_detail.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    //根据viewpager2的positionOffset来实现一个大小和颜色渐变的效果
                    //主要注意if语句判断的细节处理
                    if (mCurPosition == 0 && positionOffset > 0) {
                        mine_tv_stamp_detail_exchange_note.setTextColor(
                            ProgressUtils.evaluate(
                                positionOffset,
                                SELECTED_TEXT_COLOR,
                                NORMAL_TEXT_COLOR
                            ) as Int
                        )
                        mine_tv_stamp_detail_exchange_note.alpha =
                            (1 - TO_CHANGE_ALPHA * positionOffset).toFloat()
                        mine_tv_stamp_detail_exchange_note.textSize =
                            (16 - positionOffset * TO_CHANGE_SIZE)

                        mine_tv_stamp_detail_got_note.setTextColor(
                            ProgressUtils.evaluate(
                                positionOffset,
                                NORMAL_TEXT_COLOR,
                                SELECTED_TEXT_COLOR
                            ) as Int
                        )
                        mine_tv_stamp_detail_got_note.alpha =
                            (0.8 + TO_CHANGE_ALPHA * positionOffset).toFloat()
                        mine_tv_stamp_detail_got_note.textSize =
                            (14 + positionOffset * TO_CHANGE_SIZE)

                    }

                    if (mCurPosition == 1 && positionOffset < 1 && positionOffset != 0F) {
                        mine_tv_stamp_detail_exchange_note.setTextColor(
                            ProgressUtils.evaluate(
                                1 - positionOffset,
                                NORMAL_TEXT_COLOR,
                                SELECTED_TEXT_COLOR
                            ) as Int
                        )
                        mine_tv_stamp_detail_exchange_note.alpha =
                            (0.8 + TO_CHANGE_ALPHA * (1 - positionOffset)).toFloat()

                        mine_tv_stamp_detail_exchange_note.textSize =
                            (14 + (1 - positionOffset) * TO_CHANGE_SIZE)

                        mine_tv_stamp_detail_got_note.setTextColor(
                            ProgressUtils.evaluate(
                                1 - positionOffset,
                                SELECTED_TEXT_COLOR,
                                NORMAL_TEXT_COLOR
                            ) as Int
                        )
                        mine_tv_stamp_detail_got_note.alpha =
                            (1 - TO_CHANGE_ALPHA * (1 - positionOffset)).toFloat()
                        mine_tv_stamp_detail_got_note.textSize =
                            (16 - (1 - positionOffset) * TO_CHANGE_SIZE)
                    }

                }

                override fun onPageSelected(position: Int) {
                    //要用这个回调的Position参数 onPageScrolled从左往右滑动position值要滑到底才会变化
                    //而从右往左滑动一开始滑动position值就会减1
                    //而使用这个回调不会有问题
                    mCurPosition = position
                }
            }
        )
    }

    private fun initListener() {
        //设置俩个tab的点击事件
        //和Vp2联动
        mBinding.mineTvStampDetailExchangeNote.setOnClickListener {
            mine_vp2_stamp_detail.setCurrentItem(0, true)
        }

        mBinding.mineTvStampDetailGotNote.setOnClickListener {
            mine_vp2_stamp_detail.setCurrentItem(1, true)
        }

        //跳转的逻辑
        mBinding.mineRlStampDetailBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun initData() {

    }

    override fun initOther() {

    }


}