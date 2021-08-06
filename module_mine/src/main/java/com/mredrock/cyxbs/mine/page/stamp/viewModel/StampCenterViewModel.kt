package com.mredrock.cyxbs.mine.page.stamp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.mine.network.model.stamp.Decoration
import com.mredrock.cyxbs.mine.network.model.stamp.StampGood
import com.mredrock.cyxbs.mine.util.ui.BaseStampViewModel


private const val TAG = "StampCenterViewModel"
class StampCenterViewModel:BaseStampViewModel() {

    val title1:String
        get() = "装扮"
    val title2:String
        get() = "邮货"

    //虚拟商品
    private var _decorations = MutableLiveData<List<Decoration>>()
    val decorations: LiveData<List<Decoration>>
        get() = _decorations

    //实体商品
    private var _goods = MutableLiveData<List<StampGood>>()
    val goods: LiveData<List<StampGood>>
        get() = _goods

    //跳转到商品界面
    private var _toGoodPager = MutableLiveData<String>()
    val toGoodPager:LiveData<String>
        get() = _toGoodPager

    //跳转到明细界面
    private var _toDetailPager = MutableLiveData<Int>()
    val toDetailPager:LiveData<Int>
        get() = _toDetailPager



    fun loadDecorations(){
        val decorationList = ArrayList<Decoration>()
        repeat(6){
            decorationList.add(Decoration("title",20,"好东西","什么玩意",20,listOf("pic","pic2","pic3")))
        }
        _decorations.postValue(decorationList)
    }


    //点击title加载判断
    fun change(str:String){
        Log.d(TAG,"被点击了$str")
        when(str){
            "装扮" -> {
                if (_decorations.value?.size != 0 ){
                    _decorations.value = emptyList()
                }else{
                    loadDecorations()
                }

            }
            "邮货" ->{
                if (_goods.value?.size != 0 ){
                    _goods.value = emptyList()
                }else{
                    loadGoods()
                }
            }
        }


    }



    fun loadGoods(){
        val goodList = ArrayList<StampGood>()
        repeat(6){
            goodList.add(StampGood("title",20,"好东西",20,listOf("pic","pic2","pic3")))
        }
        _goods.postValue(goodList)
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