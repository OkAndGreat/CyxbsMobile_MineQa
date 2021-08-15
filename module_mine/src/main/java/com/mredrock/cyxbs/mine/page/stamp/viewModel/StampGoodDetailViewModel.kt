package com.mredrock.cyxbs.mine.page.stamp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.network.model.stamp.StampGood
import com.mredrock.cyxbs.mine.page.stamp.repository.StampGoodDetailRepository

class StampGoodDetailViewModel : BaseViewModel() {

    private val repository = StampGoodDetailRepository.getInstance()

    //商品
    private var _good = MutableLiveData<StampGood>()
    val good: LiveData<StampGood>
        get() = _good

    //余额
    private var _account = MutableLiveData<Int>()
    val account: LiveData<Int>
        get() = _account

    //购买信息
    private var _buyBackMessage = MutableLiveData<String>()
    val buyBackMessage: LiveData<String>
        get() = _buyBackMessage

    //是否刷新
    private var _isRefresh = MutableLiveData(0)
    val isRefresh: LiveData<Int>
        get() = _isRefresh

    fun loadGood(id: Int) {
       repository.getGoodDetail(id){
           refresh()
           _good.postValue(it)
        }
    }



    fun buyGood(id:Int){
        repository.buyGood(id,
        buyGoodBackOk = {
            _account.postValue(it.amount)
            _buyBackMessage.postValue(it.msg)
        },
        buyGoodBackError = {
            _buyBackMessage.postValue(it)
        })
    }

    fun clearGoodBuyInfo(){
        _buyBackMessage.value = "1"
        println(buyBackMessage.value)
    }

    private fun refresh(){
        _isRefresh.value = _isRefresh.value?.plus(1)
    }
}