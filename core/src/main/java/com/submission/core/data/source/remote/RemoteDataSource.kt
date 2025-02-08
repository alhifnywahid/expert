package com.submission.core.data.source.remote

import com.submission.core.data.source.remote.network.ApiResponse
import com.submission.core.data.source.remote.network.ApiService
import com.submission.core.domain.model.DataItem
import com.submission.core.domain.model.JobsDetailModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService) {
    fun getJobs(): Flow<ApiResponse<List<DataItem>>> {
        return flow {
            val response = apiService.getJobs()
            val filteredData = response.data
            if (filteredData.isNotEmpty()) {
                emit(ApiResponse.Success(filteredData))
            } else {
                emit(ApiResponse.Empty)
            }
        }
    }

    fun getJobs(id: Int): Flow<ApiResponse<JobsDetailModel>> {
        return flow {
            try {
                val response = apiService.getDetailJob(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message ?: "Unknown error"))
            }
        }
    }
}