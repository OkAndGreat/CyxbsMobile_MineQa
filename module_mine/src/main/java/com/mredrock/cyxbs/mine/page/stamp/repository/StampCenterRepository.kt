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
                listOf("pic", "pic2", "pic3")))
        }
        return decorationList
    }

    fun getStampGoodData():List<StampGood>{
        val goodList = ArrayList<StampGood>()
        repeat(6) {
            goodList.add(StampGood(listOf("http://md.udday.cn/typora/7a3479802a7f4ff09c087d019b333d53.jpeg",
                "http://md.udday.cn/typora/src=http%3A%2F%2Fwww.haitaoseo.com%2Fwp-content%2Fuploads%2F2019%2F06%2Febfc5-115R024Y-18.jpg&refer=http%3A%2F%2Fwww.haitaoseo.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg",
                "http://md.udday.cn/typora/src=http%3A%2F%2Fdingyue.ws.126.net%2FTChYPPiTy3x76D7hsDrRpMy79VYkSi5j8OgkrTY43EvWA1535552785865.jpg&refer=http%3A%2F%2Fdingyue.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg"),
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