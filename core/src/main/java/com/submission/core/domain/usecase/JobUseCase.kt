package com.submission.core.domain.usecase

import com.submission.core.data.source.remote.Resource
import com.submission.core.domain.model.Jobs
import kotlinx.coroutines.flow.Flow

interface JobUseCase {
    fun getJobs(): Flow<Resource<List<Jobs>>>

    fun getDetailJob(id: Int): Flow<Resource<Jobs>>

    //
    fun getFavJob(): Flow<List<Jobs>>

    //
    suspend fun setFavJob(job: Jobs, status: Boolean)
}