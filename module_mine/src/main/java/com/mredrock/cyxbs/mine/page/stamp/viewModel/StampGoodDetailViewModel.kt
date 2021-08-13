package com.mredrock.cyxbs.mine.page.stamp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mredrock.cyxbs.common.viewmodel.BaseViewModel
import com.mredrock.cyxbs.mine.network.model.stamp.StampGood

class StampGoodDetailViewModel : BaseViewModel() {

    //商品
    private var _good = MutableLiveData<StampGood>()
    val good: LiveData<StampGood>
        get() = _good

    fun loadGood(title: String) {
        _good.postValue(
            StampGood(listOf("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F793a1c05ddd3129c51a20a85eb532e16803645c6.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1631091878&t=b749ea1aee29ff6d7e0fa0b4926a6fb2",
                "http://md.udday.cn/typora/src=http%3A%2F%2Fwww.haitaoseo.com%2Fwp-content%2Fuploads%2F2019%2F06%2Febfc5-115R024Y-18.jpg&refer=http%3A%2F%2Fwww.haitaoseo.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg",
                "http://md.udday.cn/typora/7a3479802a7f4ff09c087d019b333d53.jpeg"
            ),
                "掌邮PM名片",
                20,
                "带上这个名片，你就是这条街最亮的仔带上这个名片，你就是这条街最亮的仔",
                "15天",
                1,
                15)
        )


    }

}