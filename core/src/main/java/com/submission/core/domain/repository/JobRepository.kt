package com.submission.core.domain.repository

import com.submission.core.data.source.remote.Resource
import com.submission.core.domain.model.Jobs
import kotlinx.coroutines.flow.Flow

interface JobRepository {
    fun getJobs(): Flow<Resource<List<Jobs>>>

    fun getDetailJob(id: Int): Flow<Resource<Jobs>>

    fun getFavJobs(): Flow<List<Jobs>>

    suspend fun setFavJob(job: Jobs, status: Boolean)
}