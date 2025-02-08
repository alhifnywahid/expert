package com.submission.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.submission.core.data.source.local.entity.JobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {
    @Insert
    fun insertFavJob(job: List<JobEntity>)

    @Update
    fun updateFavJob(job: JobEntity)

    @Query("SELECT * FROM jobs")
    fun getFavoriteJobs(): Flow<List<JobEntity>>

    @Query("SELECT * FROM jobs WHERE isFavourite = 1")
    fun getFavJob(): Flow<List<JobEntity>>
}