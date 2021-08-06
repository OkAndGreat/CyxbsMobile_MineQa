package com.mredrock.cyxbs.mine.page.sign.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.page.stamp.adapter.GoodsDetailPicAdapter
import com.mredrock.cyxbs.mine.util.ProgressUtils
import kotlinx.android.synthetic.main.mine_fragment_stamp_detail.*

/**
 * Author by OkAndGreat，Date on 2021/8/4.
 * 邮票中心兑换详情页面的fragment
 */
private const val TAG = "StampDetailFragment"

class StampDetailFragment : Fragment() {
    companion object {
        const val TO_CHANGE_SIZE = 2
        const val TO_CHANGE_ALPHA = 0.2
        const val TO_CHANGE_COLOR = 10250689
    }

    private var mCurPosition = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.mine_fragment_stamp_detail, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val NORMAL_TEXT_COLOR = Color.parseColor("#15315B")
        Log.d(TAG, "onViewCreated: 1-->$NORMAL_TEXT_COLOR")
        val SELECTED_TEXT_COLOR = Color.parseColor("#5F75E4")
        Log.d(TAG, "onViewCreated: 2-->$SELECTED_TEXT_COLOR")
        mine_vp2_stamp_detail.adapter = GoodsDetailPicAdapter()

        //监听ViewPager2来改变tab的颜色和大小
        mine_vp2_stamp_detail.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    //这段看起来长，其实逻辑并不复杂，就是根据viewpager2的positionOffset来实现一个大小和颜色渐变的效果
                    //主要是要注意if语句判断的细节
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

}