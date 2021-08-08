package com.mredrock.cyxbs.mine.page.stamp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.mine.network.model.stamp.StampTask
import com.mredrock.cyxbs.mine.page.stamp.repository.StampChangeDetailRepository
import com.mredrock.cyxbs.mine.page.stamp.repository.StampTaskRepository
import com.mredrock.cyxbs.mine.util.ui.BaseStampViewModel

class StampTaskViewModel:BaseStampViewModel() {

    private val repository:StampTaskRepository = StampTaskRepository.getInstance()

    private var _todayTasks = MutableLiveData<List<StampTask>>()
    val todayTasks: LiveData<List<StampTask>>
        get() = _todayTasks

    private var _moreTasks = MutableLiveData<List<StampTask>>()
    val moreTasks: LiveData<List<StampTask>>
        get() = _moreTasks

    private var _toTaskPager = MutableLiveData<String>()
    val toGoodPager:LiveData<String>
        get() = _toTaskPager


    fun getTodayTask(){
        _todayTasks.postValue(repository.getTodayTask())
    }

    fun getMoreTask(){
        _moreTasks.postValue(repository.getMoreTask())
    }

    fun onClickForToTaskPager(title:String){
        println("点击了")
        _toTaskPager.postValue(title)

    }
}