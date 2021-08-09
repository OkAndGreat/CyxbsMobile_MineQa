package com.mredrock.cyxbs.mine.page.stamp.viewModel

import android.util.Log
import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.mine.network.model.stamp.Decoration
import com.mredrock.cyxbs.mine.network.model.stamp.StampGood
import com.mredrock.cyxbs.mine.page.stamp.repository.StampCenterRepository
import com.mredrock.cyxbs.mine.util.ToPagerHelper
import com.mredrock.cyxbs.mine.util.ui.BaseStampViewModel
import kotlin.collections.Set as Set


private const val TAG = "StampCenterViewModel"

class StampCenterViewModel : BaseStampViewModel() {

    val title1: String
        get() = "装扮"
    val title2: String
        get() = "邮货"

    private val repository:StampCenterRepository =
        StampCenterRepository.getInstance()

    //虚拟商品
    private var _decorations = MutableLiveData<List<Decoration>>()
    val decorations: LiveData<List<Decoration>>
        get() = _decorations

    //实体商品
    private var _goods = MutableLiveData<List<StampGood>>()
    val goods: LiveData<List<StampGood>>
        get() = _goods

    //跳转到商品界面
    private var _toGoodPager = MutableLiveData<ToPagerHelper>()
    val toGoodPager: LiveData<ToPagerHelper>
        get() = _toGoodPager

    //跳转到明细界面
    private var _toDetailPager = MutableLiveData<Int>()
    val toDetailPager: LiveData<Int>
        get() = _toDetailPager


    fun loadDecorations() {
        _decorations.postValue(repository.getDecorationData())
    }

    fun loadGoods() {
        _goods.postValue(repository.getStampGoodData())
    }
    //点击title加载判断
    fun change(str: String) {
        Log.d(TAG, "被点击了$str")
        when (str) {
            "装扮" -> {
                if (_decorations.value?.size != 0) {
                    _decorations.value = emptyList()
                } else {
                    loadDecorations()
                }

            }
            "邮货" -> {
                if (_goods.value?.size != 0) {
                    _goods.value = emptyList()
                } else {
                    loadGoods()
                }
            }
        }


    }

    fun onClickForToGoodPager(title: String, type: Int) {
        println("点击了")
        _toGoodPager.postValue(ToPagerHelper(title, type))
    }

    fun onClickForToDetailPager() {
        println("点击了")
        _toDetailPager.postValue(toDetailPager.value?.plus(1))
    }
}