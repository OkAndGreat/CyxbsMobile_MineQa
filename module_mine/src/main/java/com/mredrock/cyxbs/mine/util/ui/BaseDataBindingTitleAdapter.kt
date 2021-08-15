package com.mredrock.cyxbs.mine.util.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.mine.page.stamp.adapter.GetChangeDiffUtil

/**
 *
 * @param M1 item1类
 * @param M2 item2类
 * @param DB1: ViewDataBinding item1
 * @param DB2:ViewDataBinding  item2
 * @param DBT:ViewDataBinding title
 * @property layoutId1 Int item1 xml
 * @property layoutId2 Int item2 xml
 * @property items LiveData<List<M1>> 传入livedata
 * @property items2 LiveData<List<M2>>
 * @property layoutTitleId Int title xml
 * @property title2 String title2
 * @property title1 String title1//如果title2不填入，默认显示下面那个title
 */
abstract class BaseDataBindingTitleAdapter<M1, M2, DB1 : ViewDataBinding, DB2 : ViewDataBinding, DBT : ViewDataBinding>(
    @LayoutRes private val layoutId1: Int,
    private val layoutId2: Int,
    private val items: LiveData<List<M1>>,
    private val items2: LiveData<List<M2>>,
    private val layoutTitleId: Int,
    private val lifecycleOwner: LifecycleOwner,
    private val title2: String = "无",
    private val title1: String = "无",
    scheduleLayoutAnimation: () -> Unit
) : RecyclerView.Adapter<BaseDataBindingTitleAdapter.BaseDataBindingTitleViewHolder>() {
    private var mItems = items.value
    private var mItems2 = items2.value
    private var firstInto = true

    //对传入的数据进行监听
    init {
        items.observe(lifecycleOwner, Observer { newList ->
            if (firstInto) {
                notifyDataSetChanged()
                firstInto = false
            }
            mItems?.let {
                LogUtils.d("1111", "迷糊 $it")
                DiffUtil.calculateDiff(GetChangeDiffUtil(it, newList)).dispatchUpdatesTo(this)
            }
            scheduleLayoutAnimation()
        })
        items2.observe(lifecycleOwner, Observer { newList ->
            if (firstInto) {
                notifyDataSetChanged()
                firstInto = false
            }
            mItems2?.let {
                DiffUtil.calculateDiff(GetChangeDiffUtil(it, newList)).dispatchUpdatesTo(this)
            }
            scheduleLayoutAnimation()
        })
    }

    //TYPE ID
    val ITEM_ONE = 998
    val ITEM_TWO = 999
    val TITLE_ONE = 1000
    val TITLE_TWO = 1001

    //需要确认是否有第一个title
    private var baseNumber = if (title1 == "无") {
        0
    } else {
        1
    }

    class BaseDataBindingTitleViewHolder(var mbinding: ViewDataBinding) :
        RecyclerView.ViewHolder(mbinding.root) {

    }

    override fun getItemViewType(position: Int): Int {

        items.value?.size?.let { item1Size ->
            items2.value?.size?.let { item2Size ->
                return when (position) {
                    0 -> if (baseNumber == 0) {
                        ITEM_ONE
                    } else {
                        TITLE_ONE
                    }
                    in baseNumber until item1Size -> ITEM_ONE
                    in baseNumber + item1Size until baseNumber + item1Size + 1 -> TITLE_TWO
                    in baseNumber + 1 + item1Size until baseNumber + 1 + item1Size + item2Size -> ITEM_TWO
                    else -> ITEM_ONE
                }
            }
        }
        return ITEM_ONE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseDataBindingTitleViewHolder {
        when (viewType) {
            TITLE_ONE, TITLE_TWO -> {
                val mBinding: DBT = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    layoutTitleId,
                    parent,
                    false
                )
                return BaseDataBindingTitleViewHolder(mBinding)
            }
            ITEM_ONE -> {
                val mBinding: DB1 = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    layoutId1,
                    parent,
                    false
                )
                return BaseDataBindingTitleViewHolder(mBinding)
            }
            ITEM_TWO -> {
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

        when (getItemViewType(position)) {
            TITLE_ONE, TITLE_TWO -> {
                val mBinding: DBT? = DataBindingUtil.getBinding(holder.itemView)
                if (position == 0) {
                    this.onBindTitleItem(mBinding, position, title1)
                } else {
                    this.onBindTitleItem(mBinding, position, title2)
                }
            }
            ITEM_ONE -> {
                val mBinding: DB1? = DataBindingUtil.getBinding(holder.itemView)
                items.value?.get(position - baseNumber)?.let {
                    this.onBindItem1(mBinding, it, position - baseNumber)
                }
            }
            ITEM_TWO -> {
                val mBinding: DB2? = DataBindingUtil.getBinding(holder.itemView)
                items.value?.size?.let { item1Size ->
                    items2.value?.get(position - baseNumber - 1 - item1Size)?.let {
                        this.onBindItem2(mBinding, it, position - baseNumber - 1 - item1Size)
                    }
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return items2.value?.size?.let { items.value?.size?.plus(it + 1 + baseNumber) } ?: 0
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        items.removeObservers(lifecycleOwner)
        items2.removeObservers(lifecycleOwner)
        super.onDetachedFromRecyclerView(recyclerView)
    }

    //mBinding为View，item为数据
    abstract fun onBindTitleItem(mBinding: DBT?, position: Int, title1: String)
    abstract fun onBindItem1(mBinding: DB1?, item: M1, position: Int)
    abstract fun onBindItem2(mBinding: DB2?, item: M2, position: Int)

}