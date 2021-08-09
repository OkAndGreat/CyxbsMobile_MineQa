package com.mredrock.cyxbs.mine.page.stamp.customview.progressview

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class AppBarLayoutOverScrollViewBehavior(context: Context, attrs: AttributeSet) :
    AppBarLayout.Behavior(context, attrs) {

    private val TAG = "overScroll"

    private val TARGET_HEIGHT = 9000f

    //子view
    private var mTargetView: View? = null

    //高度
    private var mParentHeight: Int = 0

    private var mTargetViewHeight: Int = 0

    private var mTotalDy: Float = 0f

    private var mLastScale: Float = 0f

    private var mLastBottom: Int = 0

    private var isAnimate: Boolean = false


    override fun onLayoutChild(
        parent: CoordinatorLayout,
        abl: AppBarLayout,
        layoutDirection: Int
    ): Boolean {
        var handled: Boolean = super.onLayoutChild(parent, abl, layoutDirection)
        if (mTargetView == null) {
            mTargetView = parent.findViewWithTag(TAG)
            if (mTargetView != null) {
                initial(abl);
            }
        }
        return handled
    }

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        isAnimate = true
        return super.onStartNestedScroll(
            parent,
            child,
            directTargetChild,
            target,
            nestedScrollAxes,
            type
        )
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if (mParentHeight != null) {
            if (mTargetView != null &&
                ((dy < 0 && child.bottom >= mParentHeight!!) ||
                        (dy > 0 && child.bottom > mParentHeight!!))
            ) {
                scale(child, target, dy)
            } else {
                super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
            }
        } else {
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        }
    }

    override fun onNestedPreFling(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (velocityY > 100) {
            isAnimate = false
        }
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        abl: AppBarLayout,
        target: View,
        type: Int
    ) {
        recovery(abl)
        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
    }

    private fun recovery(abl: AppBarLayout) {
        if (mTotalDy > 0) {
            mTotalDy = 0f
            if (isAnimate) {
                val duration = ValueAnimator.ofFloat(mLastScale, 1f).setDuration(200)
                duration.addUpdateListener {
                    ValueAnimator.AnimatorUpdateListener { animation ->
                        val animatedValue: Float = animation.animatedValue as Float
                        mTargetView?.scaleX = animatedValue
                        mTargetView?.scaleY = animatedValue
                        abl.bottom =
                            (mLastBottom - (mLastBottom - mParentHeight) * animation.animatedFraction).toInt()
                    }
                }
                duration.start();
            } else {
                mTargetView?.scaleX = 1f
                mTargetView?.scaleX = 1f
                abl.bottom = mParentHeight
            }
        }
    }


    private fun scale(abl: AppBarLayout, target: View, dy: Int) {
        mTotalDy = mTotalDy.plus(-dy);
        mTotalDy = Math.min(mTotalDy, TARGET_HEIGHT);
        mLastScale = Math.max(1f, 1f + mTotalDy / TARGET_HEIGHT);
        mTargetView?.scaleX = mLastScale
        mTargetView?.scaleY = mLastScale
        mLastBottom = mParentHeight + (mTargetViewHeight / 2 * (mLastScale - 1)).toInt()
        abl.bottom = mLastBottom
        target.scaleY = 0f
    }

    private fun initial(abl: AppBarLayout) {
        abl.clipChildren = false
        mParentHeight = abl.height
        mTargetViewHeight = mTargetView?.height!!;
    }


}