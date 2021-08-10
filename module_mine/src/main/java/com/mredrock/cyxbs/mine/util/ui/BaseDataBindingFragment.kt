package com.mredrock.cyxbs.mine.util.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.common.ui.BaseFragment


/**
 *
 * @param DB: ViewDataBinding
 * @property layoutId Int 资源文件
 * @property mBinding DB 下放的DB
 * @constructor
 */
abstract class BaseDataBindingFragment<DB: ViewDataBinding>(
    @LayoutRes private val layoutId:Int
): BaseFragment() {
    private var _mBinding: DB? = null
    val mBinding get() = _mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = DataBindingUtil.inflate(inflater,layoutId,container,false)
        _mBinding?.lifecycleOwner = this
        initView()
        initData()
        initOther()
        return mBinding.root
    }
    abstract fun initView()
    abstract fun initData()
    abstract fun initOther()
    override fun onDestroyView() {
        _mBinding?.unbind()
        _mBinding = null
        super.onDestroyView()
    }
}
