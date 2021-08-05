package com.mredrock.cyxbs.mine.page.stamp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.network.model.Goods
import com.mredrock.cyxbs.mine.util.ui.BaseStampViewModel

class StampCenterViewModel:BaseStampViewModel() {

    val title1:String
        get() = "装扮"
    val title2:String
        get() = "邮货"

    private var _goods = MutableLiveData<List<Goods>>()
    val goods: LiveData<List<Goods>>
        get() = _goods

    private var _goods2 = MutableLiveData<List<Goods>>()
    val goods2: LiveData<List<Goods>>
        get() = _goods2

    private var _toGoodPager = MutableLiveData<String>()
    val toGoodPager:LiveData<String>
        get() = _toGoodPager

    private var _toDetailPager = MutableLiveData<Int>(1)
    val toDetailPager:LiveData<Int>
        get() = _toDetailPager

    fun load(){

        val goodList = ArrayList<Goods>()
        repeat(6){
            goodList.add(Goods(listOf("pic","pic2","pic3"),"lastTime","title",10,20,"name $it","description"))
        }
        _goods.postValue(goodList)
        _goods2.postValue(goodList)
    }

    fun onClickForToGoodPager(title:String){
        println("点击了")
        _toGoodPager.postValue(title)
    }

    fun onClickForToDetailPager(){
        println("点击了")
        _toDetailPager.postValue(toDetailPager.value?.plus(1))
    }
}