package com.mredrock.cyxbs.mine.page.stamp.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.mredrock.cyxbs.common.component.CyxbsToast
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentGoodsDetailBinding
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

    private val viewModel:StampGoodDetailViewModel by viewModels()

    //banner当前的item的position
    private var mCurPosition = 0

    //注意退出后让流停止，否则会不断发送信息
    private lateinit var disposable: Disposable
    private val mRadioButtonList = ArrayList<RadioButton>()

    private var title:String = ""


    override fun initView() {
        title = arguments?.get("title") as String

        viewModel.good.observe(this, Observer {
            mBinding.mineVp2GoodsPic.adapter = GoodsDetailPicAdapter(it.pic)
            mBinding.stampGood = it
        })
        viewModel.loadGood(title)
        //设置vp2的切换动画
        mBinding.mineVp2GoodsPic.setPageTransformer(SATransformer())
        initListener()
        initCallback()
    }


    override fun initData() {
        //图片数量
        viewModel.good.observe(this, Observer {

        })
//        代码添加radioBtn,拿到数据后得到图片数量数据后代码动态添加RadioButton
//        val radioBtn = RadioButton(activity)
//        mRadioButtonList.add(radioBtn)
//
//        val layoutParams = LinearLayout.LayoutParams(6F.dp.toInt(),6F.dp.toInt())
//        layoutParams.leftMargin = 6F.dp.toInt()
//        radioBtn.setBackgroundResource(R.drawable.mine_selector_decoration_detail_radio_btn)
//        radioBtn.layoutParams = layoutParams
//
//        mBinding.mineRgGoodsDetail.addView(radioBtn)
        //数据加载

    }

    override fun initOther() {
        //初始化banner的自动轮播
        disposable = Observable.interval(3000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mBinding.mineVp2GoodsPic.setCurrentItem((mCurPosition + 1) % 3, true)
            }

    }

    @SuppressLint("InflateParams")
    private fun initListener() {
        //这里处理商品详情界面兑换按钮后被点击后的各种情况
        mBinding.mineBtnExchange.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            val inflater = LayoutInflater.from(activity)
            //加载出我们自定义的AlertDialog的布局并找到对应的view
            val view1 = inflater.inflate(R.layout.mine_dialog_goods_detail, null)
            val view2 = inflater.inflate(R.layout.mine_dialog_goods_detail_for_sure, null)
            val sureBtn = view1.findViewById(R.id.mine_btn_goods_detail_sure) as Button
            val cancelBtn = view1.findViewById(R.id.mine_btn_goods_detail_cancel) as Button
            val forSureBtn = view2.findViewById(R.id.mine_btn_goods_detail_for_sure) as Button
            val forCancelBtn = view2.findViewById(R.id.mine_btn_goods_detail_for_cancel) as Button

            val dialog = builder.create()
            dialog.window?.setWindowAnimations(R.style.mine_dialog_anim)
            dialog.show()
            val attributes = dialog.window?.attributes
            attributes?.width = 300.dp.toInt()
            attributes?.height = 178.dp.toInt()
            dialog.window?.attributes = attributes
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setContentView(view1)

            sureBtn.setOnClickListener {
                dialog.dismiss()
                dialog.setContentView(view2)
                dialog.show()
            }

            cancelBtn.setOnClickListener {
                dialog.dismiss()
            }

            //TODO:在这里处理用户请求服务器购买商品的逻辑
            forSureBtn.setOnClickListener {
                CyxbsToast.makeText(requireActivity(),"购买成功",Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }

            forCancelBtn.setOnClickListener {
                dialog.dismiss()
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
                    when (position) {
                        0 ->
                            mBinding.mineRbGoodsDetail0.isChecked = true
                        1 ->
                            mBinding.mineRbGoodsDetail1.isChecked = true
                        2 ->
                            mBinding.mineRbGoodsDetail2.isChecked = true
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

}