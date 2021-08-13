package com.mredrock.cyxbs.mine.page.stamp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.mine.network.model.stamp.CenterGood
import com.mredrock.cyxbs.mine.page.stamp.repository.StampCenterRepository
import com.mredrock.cyxbs.mine.util.ui.BaseStampViewModel


class StampCenterViewModel : BaseStampViewModel() {

    val title1: String
        get() = "装扮"
    val title2: String
        get() = "邮货"

    //记录是否是第一次进入这个界面，rv的动画
    var firstInto = 0

    private val repository: StampCenterRepository =
        StampCenterRepository.getInstance()

    //虚拟商品
    private var _decorations = MutableLiveData<List<CenterGood>>()
    val decorations: LiveData<List<CenterGood>>
        get() = _decorations

    //实体商品
    private var _goods = MutableLiveData<List<CenterGood>>()
    val goods: LiveData<List<CenterGood>>
        get() = _goods

    //跳转到商品界面
    private var _toGoodPager = MutableLiveData<String>()
    val toGoodPager: LiveData<String>
        get() = _toGoodPager

    //跳转到明细界面
    private var _toDetailPager = MutableLiveData<Int>()
    val toDetailPager: LiveData<Int>
        get() = _toDetailPager

    fun loadCenterGoods() {
        firstInto += 1
        val centerGoodData = repository.getCenterGoodData()
        _decorations.postValue(centerGoodData[0])
        _goods.postValue(centerGoodData[1])
    }

    //点击title加载判断
    fun change(str: String) {
        when (str) {
            "装扮" -> {
                if (_decorations.value?.size != 0) {
                    _decorations.value = emptyList()
                } else {
                    loadCenterGoods()
                }

            }
            "邮货" -> {
                if (_goods.value?.size != 0) {
                    _goods.value = emptyList()
                } else {
                    loadCenterGoods()
                }
            }
        }


    }

    fun onClickForToGoodPager(title: String) {
        println("点击了")
        _toGoodPager.postValue(title)
    }

    fun onClickForToDetailPager() {
        println("点击了")
        _toDetailPager.postValue(toDetailPager.value?.plus(1))
    }
}