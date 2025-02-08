package com.submission.core.domain.model

import com.google.gson.annotations.SerializedName

data class JobsDetailModel(

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("success")
    val success: Boolean
)

data class Platform(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("slug")
    val slug: String
)

data class Data(

    @field:SerializedName("job_type")
    val jobType: String,

    @field:SerializedName("offers_remote_work")
    val offersRemoteWork: Int,

    @field:SerializedName("company_id")
    val companyId: Int,

    @field:SerializedName("talent_quota")
    val talentQuota: Int,

    @field:SerializedName("business_sector")
    val businessSector: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("expiration_date")
    val expirationDate: String,

    @field:SerializedName("have_enabled_screening_question")
    val haveEnabledScreeningQuestion: Boolean,

    @field:SerializedName("location_id")
    val locationId: Int,

    @field:SerializedName("platform")
    val platform: Platform,

    @field:SerializedName("minimum_talent_experience")
    val minimumTalentExperience: String,

    @field:SerializedName("company_slug")
    val companySlug: String,

    @field:SerializedName("company_logo")
    val companyLogo: String,

    @field:SerializedName("company_name")
    val companyName: String,

    @field:SerializedName("company_employees_size")
    val companyEmployeesSize: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("slug")
    val slug: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("job_role_id")
    val jobRoleId: Int
)
