package com.mredrock.cyxbs.mine.util.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mredrock.cyxbs.common.ui.BaseFragment


/**
 *
 * @param DB: ViewDataBinding
 * @property layoutId Int
 * @property mBinding DB
 * @constructor
 */
abstract class BaseDataBindingFragment<DB: ViewDataBinding>(
    @LayoutRes private val layoutId:Int
): BaseFragment() {
    lateinit var mBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater,layoutId,container,false)
        mBinding.lifecycleOwner = this
        initView()
        initData()
        initOther()
        return mBinding.root
    }
    abstract fun initView()
    abstract fun initData()
    abstract fun initOther()
}