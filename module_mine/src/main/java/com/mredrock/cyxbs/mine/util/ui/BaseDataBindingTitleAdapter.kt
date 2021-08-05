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
        private val title1:String = "装扮",
        private val title2:String = "邮货"
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

        class BaseDataBindingTitleViewHolder(var mbinding: ViewDataBinding): RecyclerView.ViewHolder(mbinding.root) {

        }

    override fun getItemViewType(position: Int): Int {

        items.value?.size?.let { item1Size ->
            items2.value?.size?.let { item2Size ->

                return when(position){
                    0 -> TITLE_ONE
                    in 1  until 1+item1Size -> ITEM_ONE
                    in 1+item1Size until  1+item1Size -> TITLE_TWO
                    in 2+item1Size until  2+item1Size+item2Size -> ITEM_TWO
                    else -> TITLE_ONE
                }
            }
        }
        return TITLE_ONE
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
                    items.value?.get(position-1)?.let {
                        this.onBindItem1(mBinding,it,position-1)
                    };
                }
                ITEM_TWO ->{
                    val mBinding: DB2? = DataBindingUtil.getBinding(holder.itemView)
                    items.value?.size?.let { item1Size ->
                        items2.value?.get(position- 2 - item1Size)?.let {
                            this.onBindItem2(mBinding, it,position- 2 - item1Size )
                        }
                    };
                }
            }
        }



    override fun getItemCount(): Int {
            return  items2.value?.size?.let { items.value?.size?.plus(it+2) } ?: 0
        }

        //mBinding为View，item为数据
        abstract fun onBindTitleItem(mBinding: DBT?, position: Int,title1: String)
        abstract fun onBindItem1(mBinding: DB1?, item: M1,position:Int)
        abstract fun onBindItem2(mBinding: DB2?, item: M2,position:Int)


}