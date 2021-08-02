package com.mredrock.cyxbs.mine.page.sign.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.network.model.Goods
import com.mredrock.cyxbs.mine.util.ui.BaseStampViewModel

class StampViewModel : BaseStampViewModel() {

    var i = 1;


    private var _goods = MutableLiveData<List<Goods>>()
    val goods: LiveData<List<Goods>>
        get() = _goods

    private var _toGoodPager = MutableLiveData<String>()
    val toGoodPager:LiveData<String>
        get() = _toGoodPager
    //Test
    fun load(){
        i++;
        val goodList = ArrayList<Goods>()
        repeat(i){
            goodList.add(Goods(listOf("pic","pic2","pic3"),"lastTime","title",10,20,"name $it","description"))
        }
        _goods.postValue(goodList)
    }

    fun onClickForToGoodPager(title:String){
        println("点击了")
        _toGoodPager.postValue(title)
    }
}