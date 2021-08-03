package com.mredrock.cyxbs.mine.util.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView

class StampNestedScrollLayou(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int):
    NestedScrollView(context, attrs, defStyleAttr) {
    constructor(context:Context):this(context,null)
    constructor(context:Context,attrs:AttributeSet?):this(context,attrs,0)

    var contentView:ViewGroup? = null
    var topView:View? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        topView = (getChildAt(0) as ViewGroup).getChildAt(0)
        contentView  = (getChildAt(0) as ViewGroup).getChildAt(1) as ViewGroup
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val layoutParams = contentView?.layoutParams
        layoutParams?.height = measuredHeight
        contentView?.layoutParams = layoutParams
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        topView?.let {
            if (dy > 0 && scaleY < it.measuredHeight){
                scrollBy(0,dy)
                consumed[1] = dy
            }
        }
    }



}
