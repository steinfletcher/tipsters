package io.tipsters.oddsfeedclient

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface WilliamHillUkOdds {
    @GET("/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=1&marketSort=MR&filterBIR=N")
    fun matches(): Call<ResponseBody>
}

interface WilliamHillEuropeOdds {
    @GET("/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=46&marketSort=MR&filterBIR=N")
    fun matches(): Call<ResponseBody>
}