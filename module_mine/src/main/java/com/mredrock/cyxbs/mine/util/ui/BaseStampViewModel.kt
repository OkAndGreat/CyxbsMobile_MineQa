package com.mredrock.cyxbs.mine.util.ui

import androidx.lifecycle.*
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel

/**
 * 给 ViewModel生命周期管理的接口
 * @property lifecycleOwner LifecycleOwner?
 */
open class BaseStampViewModel : BaseViewModel(),
    LifecycleObserver {
    protected var lifecycleOwner: LifecycleOwner? = null

    fun attach(lifecycleOwner: LifecycleOwner){
        this.lifecycleOwner = lifecycleOwner
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onCleared() {
        super.onCleared()
        lifecycleOwner?.apply{
            lifecycleOwner!!.lifecycle.removeObserver(this@BaseStampViewModel)
            lifecycleOwner = null
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(owner: LifecycleOwner){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(owner: LifecycleOwner){
    }
}