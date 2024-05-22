package com.sopt.now.jumpit.data.remote.service

import com.sopt.now.jumpit.data.remote.response.BaseResponse
import com.sopt.now.jumpit.data.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeService {
    @GET("positions")
    suspend fun getPositions(
        @Query("keyword") keyword: String,
    ): BaseResponse<SearchResponse>
}