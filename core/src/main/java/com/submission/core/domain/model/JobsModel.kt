package com.submission.core.domain.model

import com.google.gson.annotations.SerializedName

data class JobsModel(

    @field:SerializedName("count_item_in_page")
    val countItemInPage: Int,

    @field:SerializedName("data")
    val data: List<DataItem>,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("count_per_page")
    val countPerPage: Int,

    @field:SerializedName("last_page")
    val lastPage: Int,

    @field:SerializedName("current_page")
    val currentPage: Int
)

data class DataItem(

    @field:SerializedName("job_type")
    val jobType: String,

    @field:SerializedName("company_slug")
    val companySlug: String,

    @field:SerializedName("offers_remote_work")
    val offersRemoteWork: Int,

    @field:SerializedName("company_logo")
    val companyLogo: String,

    @field:SerializedName("company_name")
    val companyName: String,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("expiration_date")
    val expirationDate: String,

    @field:SerializedName("slug")
    val slug: String,

    @field:SerializedName("minimum_talent_experience")
    val minimumTalentExperience: String,

    @field:SerializedName("salary")
    val salary: Salary
)

data class Salary(

    @field:SerializedName("min")
    val min: Int,

    @field:SerializedName("is_visible")
    val isVisible: Boolean,

    @field:SerializedName("max")
    val max: Int,

    @field:SerializedName("is_ranged_salary")
    val isRangedSalary: Boolean
)
