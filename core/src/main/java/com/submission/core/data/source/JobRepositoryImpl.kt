package com.submission.core.data.source

import com.submission.core.data.source.local.LocalDataSource
import com.submission.core.data.source.remote.NetworkBoundSource
import com.submission.core.data.source.remote.RemoteDataSource
import com.submission.core.data.source.remote.Resource
import com.submission.core.data.source.remote.network.ApiResponse
import com.submission.core.domain.model.DataItem
import com.submission.core.domain.model.Jobs
import com.submission.core.domain.repository.JobRepository
import com.submission.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class JobRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : JobRepository {
    override fun getJobs(): Flow<Resource<List<Jobs>>> =
        object : NetworkBoundSource<List<Jobs>, List<DataItem>>() {

            override suspend fun loadFromDB(): Flow<List<Jobs>> {
                return localDataSource.getJobs().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Jobs>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<DataItem>>> {
                return remoteDataSource.getJobs()
            }

            override suspend fun saveCallResult(data: List<DataItem>) {
                val jobs = DataMapper.mapResponseToEntities(data)
                localDataSource.insertJob(jobs)
            }
        }.asFlow()

    override fun getDetailJob(id: Int): Flow<Resource<Jobs>> {
        return remoteDataSource.getJobs(id).map { response ->
            when (response) {
                is ApiResponse.Success -> {
                    val job = DataMapper.mapResponseByIdToDomain(response.data.data)
                    Resource.Success(job)
                }

                is ApiResponse.Empty -> {
                    Resource.Error("")
                }

                is ApiResponse.Error -> {
                    Resource.Error(response.errorMessage)
                }
            }
        }
    }

    override fun getFavJobs(): Flow<List<Jobs>> {
        return localDataSource.getFavJobs().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavJob(job: Jobs, status: Boolean) {
        withContext(Dispatchers.IO) {
            val jobEntity = DataMapper.mapDomainToEntities(job)
            localDataSource.setFavJob(jobEntity, status)
        }
    }
}