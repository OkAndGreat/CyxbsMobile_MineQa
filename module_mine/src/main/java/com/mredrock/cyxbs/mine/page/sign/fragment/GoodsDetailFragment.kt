package com.mredrock.cyxbs.mine.page.sign.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import com.mredrock.cyxbs.mine.R
import com.mredrock.cyxbs.mine.databinding.MineFragmentGoodsDetailBinding
import com.mredrock.cyxbs.mine.page.sign.adapter.GoodsDetailPicAdapter
import com.mredrock.cyxbs.mine.util.ProgressUtils
import com.mredrock.cyxbs.mine.util.dp
import com.mredrock.cyxbs.mine.util.ui.BaseDataBindingFragment

/**
 * Author by OkAndGreat，Date on 2021/8/1.
 * 商品详情fragment
 * 根据商品是虚拟商品还是实体商品页面显示不同
 */
class GoodsDetailFragment :
    BaseDataBindingFragment<MineFragmentGoodsDetailBinding>(R.layout.mine_fragment_goods_detail) {
    override fun initView() {
        mBinding.mineVp2GoodsPic.adapter = GoodsDetailPicAdapter()

        mBinding.mineBtnExchange.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            val inflater = LayoutInflater.from(activity)
            //加载出我们自定义的AlertDialog的布局并找到对应的view
            val view = inflater.inflate(R.layout.mine_dialog_goods_detail, null)
            val btn_sure = view.findViewById(R.id.mine_btn_goods_detail_sure) as Button
            val btn_cancel = view.findViewById(R.id.mine_btn_goods_detail_cancel) as Button


            val dialog = builder.create()
            dialog.show()
            val attributes = dialog.window.attributes
            attributes.width = 300.dp.toInt()
            attributes.height = 178.dp.toInt()
            dialog.window.attributes = attributes
            dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window.setContentView(view)

            btn_sure.setOnClickListener {
                Toast.makeText(activity, "购买成功!", Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }

            btn_cancel.setOnClickListener {
                dialog.dismiss()
            }

        }
    }

    override fun initData() {

    }

    override fun initOther() {
    }

}