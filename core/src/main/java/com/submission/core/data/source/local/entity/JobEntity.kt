package com.submission.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jobs")
data class JobEntity(
    @PrimaryKey
    val id: Int,
    val jobType: String,
    val companySlug: String,
    val offersRemoteWork: Int,
    val companyLogo: String,
    val companyName: String,
    val createdAt: String,
    val location: String,
    val title: String,
    val expirationDate: String,
    val slug: String,
    val minimumTalentExperience: String,
    var isFavourite: Boolean?
)
