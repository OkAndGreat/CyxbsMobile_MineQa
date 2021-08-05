package com.mredrock.cyxbs.mine.page.stamp.customview.progressview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

/**
 * Author by OkAndGreat，Date on 2021/8/2.
 * 进度条自定义View
 */
class ProgressView(context: Context) :
    View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mPaintStrokeWidth = 46.toFloat()

    //进度条背景颜色
    private val bgColor = -0x1e1a18

    //动画执行时间
    private val duration = 5000L

    // 进度条颜色
    private val progressColor = -0x994ee

    //Count +1时进度条应该增长的长度
    private var averageWidth = 0F

    private var maxCount = 0
    private var curCount = 0

    private lateinit var Animator: ValueAnimator

    //当前的进度条宽度
    private var curBarWidth = 0F

    init {
        //初始化画笔
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = mPaintStrokeWidth
            strokeCap = Paint.Cap.ROUND
            textAlign = Paint.Align.CENTER
        }
    }

    //将View的高度设置成边框的宽度
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        averageWidth = (width / maxCount).toFloat()
        setMeasuredDimension(width, mPaintStrokeWidth.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        //View的宽度和高度
        val width = width
        val height = height

        //画进度条背景
        paint.color = bgColor
        canvas.drawLine(
            paddingLeft.toFloat(),
            (height / 2).toFloat(),
            width.toFloat(),
            (height / 2).toFloat(),
            paint
        )

        //画进度条
        paint.color = progressColor
        if (curBarWidth != 0F)
            canvas.drawLine(
                paddingLeft.toFloat(),
                (height / 2).toFloat(),
                curBarWidth,
                (height / 2).toFloat(),
                paint
            )
    }

    fun setCurCount(Count: Int) {
        startAnim(curCount, Count)
        curCount = Count
    }

    fun setMaxCount(Count: Int) {
        maxCount = Count
    }

    private fun startAnim(oldCount: Int, newCount: Int) {
        Animator = ValueAnimator.ofFloat(averageWidth * oldCount, averageWidth * newCount)
        Animator.duration = duration
        Animator.interpolator = AccelerateDecelerateInterpolator()
        Animator.addUpdateListener {
            curBarWidth = it.animatedValue as Float
            invalidate()
        }

        Animator.start()
    }
}