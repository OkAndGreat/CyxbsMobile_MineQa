package com.mredrock.cyxbs.mine.page.stamp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.mine.network.model.stamp.CenterGood
import com.mredrock.cyxbs.mine.network.model.stamp.StampTask
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

    //今日任务
    private var _todayTasks = MutableLiveData<List<StampTask>>()
    val todayTasks: LiveData<List<StampTask>>
        get() = _todayTasks

    //更多任务
    private var _moreTasks = MutableLiveData<List<StampTask>>()
    val moreTasks: LiveData<List<StampTask>>
        get() = _moreTasks

    //账户余额
    private var _userAccount = MutableLiveData<Int>()
    val userAccount: LiveData<Int>
        get() = _userAccount

    //是否有未领取的商品
    private var _unGotGood = MutableLiveData<Boolean>()
    val unGotGood:LiveData<Boolean>
        get() = _unGotGood

    //跳转到商品界面
    private var _toGoodPager = MutableLiveData<Int>()
    val toGoodPager: LiveData<Int>
        get() = _toGoodPager

    //跳转到明细界面
    private var _toDetailPager = MutableLiveData<Int>()
    val toDetailPager: LiveData<Int>
        get() = _toDetailPager

    fun loadCenterGoods() {
        firstInto += 1
        repository.getCenterGoodData(
            goods = {
            _goods.postValue(it) },
            decorations = {
            _decorations.postValue(it) },
            todayTasks = {
            _todayTasks.postValue(it) },
            moreTasks = {
            _moreTasks.postValue(it) },
            userAccount = {
            _userAccount.postValue(it) },
            unGotGood = {
            _unGotGood.postValue(it) }
        )
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

    fun onClickForToGoodPager(id: Int) {
        _toGoodPager.postValue(id)
    }

    fun onClickForToDetailPager() {
        _toDetailPager.postValue(toDetailPager.value?.plus(1))
    }
}