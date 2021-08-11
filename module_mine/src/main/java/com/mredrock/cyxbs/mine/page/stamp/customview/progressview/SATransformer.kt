package com.mredrock.cyxbs.mine.page.stamp.customview.progressview

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class SATransformer : ViewPager2.PageTransformer {
    private val MIN_SCALE = 0.7f
    private val MIN_ALPHA = 0.5f


    override fun transformPage(page: View, position: Float) {
        if (position < -1 || position > 1) {
            page.alpha = MIN_ALPHA
            page.scaleX = MIN_SCALE
            page.scaleY = MIN_SCALE
        } else if (position <= 1) {
            var scaleFactor = MIN_SCALE.coerceAtLeast(1 - abs(position))
            if (position < 0) {
                var mScaleX = 1 + 0.3f * position
                page.scaleX = mScaleX
                page.scaleY = mScaleX
            } else {
                var mScaleX = 1 - 0.3f * position
                page.scaleX = mScaleX
                page.scaleY = mScaleX
            }
            page.alpha = MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)
        }
    }
}