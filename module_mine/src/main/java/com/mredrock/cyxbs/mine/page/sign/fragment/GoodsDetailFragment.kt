package com.mredrock.cyxbs.mine.page.sign.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentGoodsDetailBinding
import com.mredrock.cyxbs.mine.page.stamp.adapter.GoodsDetailPicAdapter
import com.mredrock.cyxbs.mine.util.dp
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment

/**
 * Author by OkAndGreat，Date on 2021/8/1.
 * 商品详情fragment
 * 根据商品是虚拟商品还是实体商品页面显示不同
 * 如果是虚拟商品要通过代码修改权益说明textview的值
 */
class GoodsDetailFragment :
    BaseDataBindingFragment<MineFragmentGoodsDetailBinding>(R.layout.mine_fragment_goods_detail) {
    private val mRadioButtonList = ArrayList<RadioButton>()
    override fun initView() {
        mBinding.mineVp2GoodsPic.adapter = GoodsDetailPicAdapter()


        initListener()
        initCallback()
    }


    override fun initData() {
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
    }

    override fun initOther() {
    }

    private fun initListener() {
        //这里的代码很多，但逻辑其实很简单，就是处理商品详情界面兑换按钮后被点击后的各种情况
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
            dialog.show()
            val attributes = dialog.window.attributes
            attributes.width = 300.dp.toInt()
            attributes.height = 178.dp.toInt()
            dialog.window.attributes = attributes
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window.setContentView(view1)

            sureBtn.setOnClickListener {
                dialog.setContentView(view2)
            }

            cancelBtn.setOnClickListener {
                dialog.dismiss()
            }

            //TODO:在这里处理用户请求服务器购买商品的逻辑
            forSureBtn.setOnClickListener {
                Toast.makeText(activity,"购买成功",Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }

            forCancelBtn.setOnClickListener {
                dialog.dismiss()
            }
        }

        //处理back键点击的交互逻辑
        mBinding.mineViewStampDetailBack.setOnClickListener {
            activity?.onBackPressed()
        }


    }

    private fun initCallback() {
        //viewPager2操作，与RadioButton联系
        mBinding.mineVp2GoodsPic.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    Log.d(
                        TAG,
                        "onPageScrolled:\n " +
                                "position-->$position \n " +
                                "positionOffset-->$positionOffset"
                    )

                    //打Log可以发现从左往右滑动时滑倒末尾position才会变化并且positionOffset值是从0变化到1，
                    // 而从右往左滑positionOffset值是从1变化到0，一开始滑动position值就会发生变化
                    // 如果不注意这个问题并进行处理非常容易出bug

                }

                override fun onPageSelected(position: Int) {
                    Log.d(
                        TAG,
                        "onPageSelected:\n " +
                                "position-->$position \n "
                    )

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

}