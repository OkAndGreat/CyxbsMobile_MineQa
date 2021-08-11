package com.mredrock.cyxbs.mine.page.stamp.repository

import com.mredrock.cyxbs.mine.network.model.stamp.Decoration
import com.mredrock.cyxbs.mine.network.model.stamp.StampGood

class StampCenterRepository private constructor(){
    companion object {
        fun getInstance(): StampCenterRepository {
            return Holder.instance
        }
    }
    private object Holder {
        val instance = StampCenterRepository()
    }


    fun getDecorationData():List<Decoration>{
        val decorationList = ArrayList<Decoration>()
        repeat(6) {
            decorationList.add(Decoration("title+$it",
                20,
                "好东西",
                "什么玩意",
                0,
                20,
                listOf("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F770f139892f2c3d06879182c47e077104c02e085.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1631091734&t=2bacd69f90e442a728f0073d1bde0a27", "pic2", "pic3")))
        }
        return decorationList
    }

    fun getStampGoodData():List<StampGood>{
        val goodList = ArrayList<StampGood>()
        repeat(6) {
            goodList.add(StampGood(listOf("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F793a1c05ddd3129c51a20a85eb532e16803645c6.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1631091878&t=b749ea1aee29ff6d7e0fa0b4926a6fb2"
                ,"http://md.udday.cn/typora/src=http%3A%2F%2Fwww.haitaoseo.com%2Fwp-content%2Fuploads%2F2019%2F06%2Febfc5-115R024Y-18.jpg&refer=http%3A%2F%2Fwww.haitaoseo.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg",
                "http://md.udday.cn/typora/7a3479802a7f4ff09c087d019b333d53.jpeg"
            ),
                "名字+$it  ",
                20,
                "好东西",
                "什么玩意",
                1,
                15))
        }
        return goodList
    }
}