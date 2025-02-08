package com.submission.core.data.source.remote.network

import com.submission.core.domain.model.JobsDetailModel
import com.submission.core.domain.model.JobsModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("vacancies")
    suspend fun getJobs(
        @Query("page") page: Int = 1,
        @Query("item_per_page") itemsPerPage: Int = 100,
        @Query("platform") platform: String = "dicodingjobs"
    ): JobsModel

    @GET("vacancies/detail")
    suspend fun getDetailJob(
        @Query("id") id: Int
    ): JobsDetailModel


}