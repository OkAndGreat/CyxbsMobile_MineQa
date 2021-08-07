package com.mredrock.cyxbs.mine.page.stamp.customview.progressview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.mredrock.cyxbs.mine.util.DisplayUtils

/**
 * Author by OkAndGreat，Date on 2021/8/2.
 * 进度条自定义View
 */
class ProgressView(context: Context) :
    View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mPaintStrokeWidth = 0F

    //进度条背景颜色
    private val bgColor = -0x1e1a18

    //动画执行时间
    private val duration = 5000L

    // 进度条颜色
    private var progressColor = -0x994ee

    //Count +1时进度条应该增长的长度
    private var averageWidth = 0F

    private var maxCount = 0
    private var curCount = 0

    //背景条的RectF
    private val mBackGroundRectF = RectF()

    //进度条的RectF
    private val mProgressRectF = RectF()

    private lateinit var Animator: ValueAnimator

    //当前的进度条宽度
    private var curBarWidth = 0F



    init {
        mPaintStrokeWidth = DisplayUtils.dp2px(context, 8F).toFloat()
        progressColor = Color.parseColor("#7D8AFF")
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
        //项目需求 宽度写死 150dp
        val width = DisplayUtils.dp2px(context, 150F)
        if(maxCount != 0){
            averageWidth = (width / maxCount).toFloat()
        }

        setMeasuredDimension(width, mPaintStrokeWidth.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        //View的宽度和高度
        val width = width
        val height = height


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