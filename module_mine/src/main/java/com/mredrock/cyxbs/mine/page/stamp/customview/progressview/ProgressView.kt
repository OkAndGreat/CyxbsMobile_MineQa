package com.mredrock.cyxbs.mine.page.stamp.customview.progressview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.mredrock.cyxbs.mine.page.stamp.fragment.StampDetailFragment
import com.mredrock.cyxbs.mine.util.DisplayUtils

/**
 * Author by OkAndGreat，Date on 2021/8/2.
 * 进度条自定义View
 */
class ProgressView(context: Context) :
    View(context) {
    companion object {
        //是否处于深色模式或者浅色模式的判断值
        const val DARK_MODE = 0x21
        const val LIGHT_MODE = 0x11
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mPaintStrokeWidth = 0F

    //进度条背景颜色
    private var bgColor = -0x1e1a18

    //动画执行时间
    private val duration = 2000L

    // 进度条颜色
    private var progressColor = -0x994ee

    //Count +1时进度条应该增长的长度
    private var averageWidth = 0F

    //进度条最大值和当前值
    private var maxCount = 0
    private var curCount = 0

    //背景条的RectF
    private val mBackGroundRectF = RectF()

    //进度条的RectF
    private val mProgressRectF = RectF()

    private lateinit var progressAnimator: ValueAnimator

    //当前的进度条宽度
    private var curBarWidth = 0F


    //初始化画笔的属性

    init {
        progressColor = Color.parseColor("#7D8AFF")
        mPaintStrokeWidth = DisplayUtils.dp2px(context, 8F).toFloat()
        if (context.applicationContext.resources.configuration.uiMode == StampDetailFragment.DARK_MODE) {
            bgColor = Color.parseColor("#454545")
        }


        //初始化画笔
        paint.apply {
            style = Paint.Style.FILL
            strokeWidth = mPaintStrokeWidth
            strokeCap = Paint.Cap.ROUND
            textAlign = Paint.Align.CENTER
        }
    }

    //将View的高度设置成边框的宽度
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //因为这个自定义View是自己写的并且自己用，不用给别人用所以这里 宽度写死了 是 150dp
        val width = DisplayUtils.dp2px(context, 150F)
        if (maxCount != 0) {
            averageWidth = (width / maxCount).toFloat()
        }

        setMeasuredDimension(width, mPaintStrokeWidth.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        //View的宽度
        val width = width

        //画进度条背景
        mBackGroundRectF.set(
            paddingLeft.toFloat(),
            5F,
            width.toFloat(),
            mPaintStrokeWidth
        )
        paint.color = bgColor
        canvas.drawRoundRect(mBackGroundRectF, 15F, 15F, paint)


        //画进度条
        paint.color = progressColor
        if (curBarWidth != 0F) {
            mProgressRectF.set(
                paddingLeft.toFloat(),
                6F,
                curBarWidth,
                mPaintStrokeWidth
            )
            canvas.drawRoundRect(mProgressRectF, 15F, 15F, paint)
        }


    }

    //设置进度条值，设置之后开始进度条增加的动画
    fun setCurCount(Count: Int) {
        startAnim(curCount, Count)
        curCount = Count
    }

    //最大值设置一次即可
    fun setMaxCount(Count: Int) {
        maxCount = Count
    }

    private fun startAnim(oldCount: Int, newCount: Int) {
        progressAnimator = ValueAnimator.ofFloat(averageWidth * oldCount, averageWidth * newCount)
        progressAnimator.duration = duration
        progressAnimator.interpolator = AccelerateDecelerateInterpolator()
        progressAnimator.addUpdateListener {
            curBarWidth = it.animatedValue as Float
            invalidate()
        }

        progressAnimator.start()
    }
}