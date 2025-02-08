package com.submission.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jobs(
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
    val description: String = "-",
    val isFavourite: Boolean?
) : Parcelable