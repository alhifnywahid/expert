package com.submission.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.submission.core.data.source.local.entity.JobEntity

@Database(entities = [JobEntity::class], version = 1, exportSchema = false)
abstract class JobDatabase : RoomDatabase() {
    abstract fun favouriteDao(): JobDao
}