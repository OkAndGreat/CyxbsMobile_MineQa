package com.mredrock.cyxbs.mine.util.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDataBindingTitleAdapter<M1,M2,DB1: ViewDataBinding,DB2:ViewDataBinding,DBT:ViewDataBinding>(
        @LayoutRes private val layoutId1:Int,
        private val layoutId2:Int,
        private val items : LiveData<List<M1>>,
        private val items2 : LiveData<List<M2>>,
        private val layoutTitleId:Int,
        lifecycleOwner: LifecycleOwner,
        private val title2:String = "无",
        private val title1:String = "无"
    ): RecyclerView.Adapter<BaseDataBindingTitleAdapter.BaseDataBindingTitleViewHolder>() {
        init {
            items.observe(lifecycleOwner, Observer{
                notifyDataSetChanged()
            })
            items2.observe(lifecycleOwner, Observer {
                notifyDataSetChanged()
            })
        }

        val ITEM_ONE = 998
        val ITEM_TWO = 999
        val TITLE_ONE = 1000
        val TITLE_TWO = 1001

        private var baseNumber = if ( title1 == "无" ){ 0 } else { 1 }

        class BaseDataBindingTitleViewHolder(var mbinding: ViewDataBinding): RecyclerView.ViewHolder(mbinding.root) {

        }

    override fun getItemViewType(position: Int): Int {

        items.value?.size?.let { item1Size ->
            items2.value?.size?.let { item2Size ->

                return when(position){
                    0 ->if ( baseNumber == 0 ){
                            ITEM_ONE
                        }else{
                            TITLE_ONE
                        }
                    in baseNumber  until item1Size -> ITEM_ONE
                    in baseNumber+item1Size until  baseNumber+item1Size+1 -> TITLE_TWO
                    in baseNumber+1+item1Size until  baseNumber+1+item1Size+item2Size -> ITEM_TWO
                    else -> ITEM_ONE
                }
            }
        }
        return ITEM_ONE
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseDataBindingTitleViewHolder {
              when(viewType){
                TITLE_ONE,TITLE_TWO -> {
                    val mBinding: DBT = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        layoutTitleId,
                        parent,
                        false
                    )
                    return BaseDataBindingTitleViewHolder(mBinding)
                }
                ITEM_ONE ->{
                    val mBinding: DB1 = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        layoutId1,
                        parent,
                        false
                    )
                    return BaseDataBindingTitleViewHolder(mBinding)
                }
                ITEM_TWO ->{
                    val mBinding: DB2 = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        layoutId2,
                        parent,
                        false
                    )
                    return BaseDataBindingTitleViewHolder(mBinding)
                }
                  else -> {
                      val mBinding: DB1 = DataBindingUtil.inflate(
                          LayoutInflater.from(parent.context),
                          layoutId1,
                          parent,
                          false
                      )
                      return BaseDataBindingTitleViewHolder(mBinding)
                  }
              }
        }

        override fun onBindViewHolder(holder: BaseDataBindingTitleViewHolder, position: Int) {

            when(getItemViewType(position)){
                TITLE_ONE,TITLE_TWO ->{
                    val mBinding: DBT? = DataBindingUtil.getBinding(holder.itemView)
                            if ( position == 0){
                                this.onBindTitleItem(mBinding,position,title1)
                            }else{
                                this.onBindTitleItem(mBinding,position,title2)
                            }
                }
                ITEM_ONE ->{
                    val mBinding: DB1? = DataBindingUtil.getBinding(holder.itemView)
                    items.value?.get(position-baseNumber)?.let {
                        this.onBindItem1(mBinding,it,position-baseNumber)
                    }
                }
                ITEM_TWO ->{
                    val mBinding: DB2? = DataBindingUtil.getBinding(holder.itemView)
                    items.value?.size?.let { item1Size ->
                        items2.value?.get(position- baseNumber - 1 - item1Size)?.let {
                            this.onBindItem2(mBinding, it,position- baseNumber - 1 - item1Size )
                        }
                    }
                }
            }
        }



    override fun getItemCount(): Int {
            return  items2.value?.size?.let { items.value?.size?.plus(it+1+baseNumber) } ?: 0
        }

        //mBinding为View，item为数据
        abstract fun onBindTitleItem(mBinding: DBT?, position: Int,title1: String)
        abstract fun onBindItem1(mBinding: DB1?, item: M1,position:Int)
        abstract fun onBindItem2(mBinding: DB2?, item: M2,position:Int)


}