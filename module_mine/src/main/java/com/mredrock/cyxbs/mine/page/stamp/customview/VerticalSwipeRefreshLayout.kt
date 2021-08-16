package com.mredrock.cyxbs.mine.page.stamp.customview

import android.content.Context
import android.util.AttributeSet
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.view.MotionEvent
import kotlin.math.abs


/**
 * Author by OkAndGreat，Date on 2021/8/16.
 *
 */
class VerticalSwipeRefreshLayout(context:Context,attrs:AttributeSet) :SwipeRefreshLayout(context,attrs) {
    private var mTouchSlop = 0
    // 上一次触摸时的X坐标
    private var mPrevX = 0f

    init {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> mPrevX = event.x
            MotionEvent.ACTION_MOVE -> {
                val eventX = event.x
                val xDiff = abs(eventX - mPrevX)
                // 增加60的容差，让下拉刷新在竖直滑动时就可以触发
                if (xDiff > mTouchSlop + 60) {
                    return false
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }
}