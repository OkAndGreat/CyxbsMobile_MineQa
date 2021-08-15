package com.mredrock.cyxbs.mine.page.stamp.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentGoodsDetailBinding
import com.mredrock.cyxbs.mine.network.model.stamp.StampGood
import com.mredrock.cyxbs.mine.page.stamp.adapter.GoodsDetailPicAdapter
import com.mredrock.cyxbs.mine.page.stamp.customview.SATransformer
import com.mredrock.cyxbs.mine.page.stamp.viewModel.StampGoodDetailViewModel
import com.mredrock.cyxbs.mine.util.dp
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * Author by OkAndGreat，Date on 2021/8/1.
 * 商品详情fragment
 * 根据商品是虚拟商品还是实体商品页面显示不同
 * 如果是虚拟商品要通过代码修改权益说明textview的值
 */

class StampGoodsDetailFragment :
    BaseDataBindingFragment<MineFragmentGoodsDetailBinding>(R.layout.mine_fragment_goods_detail) {

    private val viewModel: StampGoodDetailViewModel by viewModels()

    //banner当前的item的position
    private var mCurPosition = 0

    //注意退出后让流停止，否则会不断发送信息
    private lateinit var disposable: Disposable
    private val mRadioButtonList = ArrayList<RadioButton>()

    //传入的商品id
    private var mId: Int = 0

    //余额
    private var mAccount: Int = 0

    //图片的数量，不能为0，否则可能banner轮播会出现除0错误
    private var mPicCount = 1

    //商品剩余数量
    private var mGoodAccount: Int = 0

    //商品价格
    private var mGood: StampGood = StampGood("", 99999, 0, listOf(), 0, "", "")


    override fun initView() {
        mId = arguments?.get("id") as Int
        //设置传进的余额
        mAccount = arguments?.get("account") as Int
        refreshMAccount()

        viewModel.good.observe(this, Observer {
            mBinding.mineVp2GoodsPic.adapter = GoodsDetailPicAdapter(it.pic)
            mBinding.stampGood = it
            mGood = it
        })

        viewModel.account.observe(this, Observer {
            mGoodAccount = it
            mBinding.mineStampDecorationInventory.text = "$it"
        })

        viewModel.isRefresh.observe(this, Observer {
            println("111 $it")
            mBinding.mineFlGoodsSf.isRefreshing = false
        })

        viewModel.loadGood(mId)

        mBinding.mineFlGoodsSf.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                viewModel.loadGood(mId)
            }
        })
        //设置vp2的切换动画
        mBinding.mineVp2GoodsPic.setPageTransformer(SATransformer())
        initListener()
        initCallback()
    }


    override fun initData() {
        //图片数量
        viewModel.good.observe(this, Observer {
            //代码添加radioBtn,拿到数据后得到图片数量数据后代码动态添加RadioButton,要求图片数量大于1张
            if (it.pic.size > 1) {
                mPicCount = 0
                for (i in it.pic.indices) {
                    val radioBtn = RadioButton(activity)
                    mRadioButtonList.add(radioBtn)
                    val layoutParams = LinearLayout.LayoutParams(6F.dp.toInt(), 6F.dp.toInt())
                    layoutParams.leftMargin = 6F.dp.toInt()
                    radioBtn.setBackgroundResource(R.drawable.mine_selector_decoration_detail_radio_btn)
                    radioBtn.layoutParams = layoutParams
                    mBinding.mineRgGoodsDetail.addView(radioBtn)
                    mPicCount++
                }
            }

        })

        //数据加载

    }

    override fun initOther() {
        //初始化banner的自动轮播
        disposable = Observable.interval(3000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mBinding.mineVp2GoodsPic.setCurrentItem((mCurPosition + 1) % mPicCount, true)
            }

        //如果图片只有一张,关闭RX流
        if (mPicCount == 1) {
            disposable.dispose()
        }
    }

    @SuppressLint("InflateParams")
    private fun initListener() {


        mBinding.mineBtnExchange.setOnClickListener {

            //这里处理商品详情界面兑换按钮后被点击后的各种情况
            val builder = AlertDialog.Builder(activity)
            val inflater = LayoutInflater.from(activity)
            //加载出我们自定义的AlertDialog的布局并找到对应的view
            val view1 = inflater.inflate(R.layout.mine_dialog_goods_detail, null)
            val view1Tv =
                view1.findViewById(R.id.mine_tv_goods_detail_dialog_content) as AppCompatTextView
            val sureBtn = view1.findViewById(R.id.mine_btn_goods_detail_sure) as Button
            val cancelBtn = view1.findViewById(R.id.mine_btn_goods_detail_cancel) as Button

            val view2 = inflater.inflate(R.layout.mine_dialog_goods_detail_for_sure, null)
            val view2Tv =
                view2.findViewById(R.id.mine_tv_goods_detail_dialog_content_sure) as AppCompatTextView
            val forSureBtn = view2.findViewById(R.id.mine_btn_goods_detail_for_sure) as Button
            val forCancelBtn = view2.findViewById(R.id.mine_btn_goods_detail_for_cancel) as Button

            val view3 = inflater.inflate(R.layout.mine_dialog_goods_detail_over, null)
            val view3Tv =
                view3.findViewById(R.id.mine_tv_goods_detail_dialog_content_over) as AppCompatTextView
            val overBtn = view3.findViewById(R.id.mine_btn_goods_detail_over) as Button

            val dialog = builder.create()
            dialog.window?.setWindowAnimations(R.style.mine_dialog_anim)
            dialog.setContentView(view1)
            dialog.show()
            val attributes = dialog.window?.attributes
            attributes?.width = 300.dp.toInt()
            attributes?.height = 178.dp.toInt()
            dialog.window?.attributes = attributes
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //初始化
            view1Tv.text = "确认要用${mGood.price}邮豆兑换${mGood.title}吗？"
            dialog.window?.setContentView(view1)


            //这里是不满足购买条件的时候的按钮
            overBtn.setOnClickListener {
                dialog.dismiss()
            }


            //这里是第一个弹窗的逻辑
            sureBtn.setOnClickListener {
                dialog.dismiss()
                if (mGood.amount > 0) {
                    if (mAccount > mGood.price) {
                        LogUtils.d("111111", "好家伙")
                        viewModel.buyGood(mId)
                    } else {
                        view3Tv.text = "诶.......邮票不够啊......穷日子真不好过呀QAQ"
                        dialog.setContentView(view3)
                        dialog.show()
                    }
                } else {
                    view3Tv.text = "啊欧，手慢了！下次再来吧=.="
                    dialog.setContentView(view3)
                    dialog.show()
                }

            }

            //这里是第一个弹窗取消购买的按钮
            cancelBtn.setOnClickListener {
                dialog.dismiss()
            }

            //TODO 这里设置装扮
            forSureBtn.setOnClickListener {
                dialog.dismiss()
            }

            forCancelBtn.setOnClickListener {
                dialog.dismiss()
            }
            if (!viewModel.buyBackMessage.hasActiveObservers()) {
                viewModel.buyBackMessage.observe(this, Observer {
                    Log.d("111111111", it)
                    when (it) {
                        "兑换成功" -> {
                            //1是实体，0是虚拟
                            if (mGood.type == 1) {
                                view3Tv.text = "兑换成功！请在${mGood.life}天内到红岩网校领取哦"
                                dialog.setContentView(view3)
                                dialog.show()
                                mAccount -= mGood.price
                                refreshMAccount()
                            } else {
                                view2Tv.text = "兑换成功！现在就换掉原来的名片吧！"
                                dialog.setContentView(view2)
                                dialog.show()
                                mAccount -= mGood.price
                                refreshMAccount()
                            }
                        }
                        "reduce goods error" -> {
                            view3Tv.text = "啊欧，手慢了！下次再来吧=.="
                            dialog.setContentView(view3)
                            dialog.show()
                        }
                        "Integral not enough" -> {
                            view3Tv.text = "诶.......邮票不够啊......穷日子真不好过呀QAQ"
                            dialog.setContentView(view3)
                            dialog.show()
                        }
                        else -> {
                            dialog.dismiss()
                        }
                    }
                })
            }


        }

        //处理back键点击的交互逻辑
        mBinding.mineRlStampCenterBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initCallback() {
        //viewPager2操作，与RadioButton联系
        //使用onPageScrolled这个方法打Log可以发现从左往右滑动时滑倒末尾position才会变化并且positionOffset值是从0变化到1，
        // 而从右往左滑positionOffset值是从1变化到0，一开始滑动position值就会发生变化
        // 如果不注意这个问题并进行处理非常容易出bug
        //因此使用onPageSelected再当前需求下更合理
        mBinding.mineVp2GoodsPic.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    mCurPosition = position
                    if (mPicCount > 1) {
                        mRadioButtonList[position].isChecked = true
                    }
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    private fun refreshMAccount() {
        mBinding.mineTvDecorationRestCount.text = "余额：$mAccount"
    }
}