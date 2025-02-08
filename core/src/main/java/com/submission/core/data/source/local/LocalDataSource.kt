package com.submission.core.data.source.local

import com.submission.core.data.source.local.entity.JobEntity
import com.submission.core.data.source.local.room.JobDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LocalDataSource(private val jobDao: JobDao) {
    fun getJobs(): Flow<List<JobEntity>> = jobDao.getFavoriteJobs()

    fun getFavJobs(): Flow<List<JobEntity>> = jobDao.getFavJob()

    suspend fun insertJob(jobList: List<JobEntity>) {
        withContext(Dispatchers.IO) {
            jobDao.insertFavJob(jobList)
        }
    }

    fun setFavJob(job: JobEntity, newState: Boolean) {
        job.isFavourite = newState
        jobDao.updateFavJob(job)
    }
}