package com.mredrock.cyxbs.mine.page.sign.customview.progressview

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import com.mredrock.cyxbs.mine.R

/**
 * Author by OkAndGreat，Date on 2021/8/2.
 * 将ProgressView和CountView竖直方向合并到一起的自定义Layout
 */
class ProgressLayout(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private lateinit var mProgressView: ProgressView
    private lateinit var mCountView: CountView

    private var maxCount = 0
    private var curCount = 0

    init {
        init()
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ProgressLayout)
        maxCount = typedArray.getInt(R.styleable.ProgressLayout_max_count, 0)
        curCount = typedArray.getInt(R.styleable.ProgressLayout_cur_count, 0)
        setCurCount(curCount)
        setMaxCount(maxCount)
        typedArray.recycle()
    }

    private fun init() {
        removeAllViews()
        orientation = VERTICAL

        addCountView()
        mCountView.setCount(0)
        addProgressView()
    }

    //将ProgressView添加到线性布局中
    private fun addProgressView() {
        mProgressView = ProgressView(context)
        addView(mProgressView, getProgressViewParams())
    }

    //params.xxxMargin = paddingxxx 是为了处理 ProgressLayout设置padding时它内部控件的摆放情况
    //生成ProgressView的布局参数
    private fun getProgressViewParams(): LayoutParams {
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params.topMargin = paddingTop
        params.leftMargin = paddingLeft
        params.rightMargin = paddingRight
        return params
    }

    //将CountView添加到线性布局中
    private fun addCountView() {
        mCountView = CountView(context)
        addView(mCountView, getCountViewParams())
    }

    //生成CountView的布局参数
    private fun getCountViewParams(): LayoutParams {
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.leftMargin = paddingLeft
        params.rightMargin = paddingRight
        //这里加20的目的是为了让CountView和进度条View有一定间隔
        params.bottomMargin = paddingBottom + 20
        params.gravity = Gravity.CENTER
        return params
    }

    //进入页面拿到后端数据后应该马上调这个方法来设置当前的数量
    //如果没有设置，则默认为0
    //即  0  /  maxCount
    fun setCurCount(Count: Int) {
        mProgressView.setCurCount(Count)
        mCountView.calculateChangeNum(Count)
    }

    fun setMaxCount(Count: Int) {
        mProgressView.setMaxCount(Count)
        mCountView.setMaxCount(Count)
    }
}