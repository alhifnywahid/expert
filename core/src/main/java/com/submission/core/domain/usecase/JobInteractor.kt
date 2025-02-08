package com.submission.core.domain.usecase

import com.submission.core.data.source.remote.Resource
import com.submission.core.domain.model.Jobs
import com.submission.core.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow

class JobInteractor(private val jobRepository: JobRepository) : JobUseCase {
    override fun getJobs(): Flow<Resource<List<Jobs>>> {
        return jobRepository.getJobs()
    }

    override fun getDetailJob(id: Int): Flow<Resource<Jobs>> {
        return jobRepository.getDetailJob(id)
    }

    override fun getFavJob(): Flow<List<Jobs>> {
        return jobRepository.getFavJobs()
    }

    override suspend fun setFavJob(job: Jobs, status: Boolean) {
        return jobRepository.setFavJob(job, status)
    }
}