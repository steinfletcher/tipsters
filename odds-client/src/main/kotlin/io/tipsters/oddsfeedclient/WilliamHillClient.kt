package io.tipsters.oddsfeedclient

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface WilliamHillClient {
    @GET("http://cachepricefeeds.williamhill.com/openbet_cdn?action=template&template=getHierarchyByMarketType&classId=1&marketSort=MR&filterBIR=N")
    fun ukMatches(): Call<ResponseBody>
}