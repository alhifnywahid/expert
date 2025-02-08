package com.submission.core.utils

import com.submission.core.data.source.local.entity.JobEntity
import com.submission.core.domain.model.Data
import com.submission.core.domain.model.DataItem
import com.submission.core.domain.model.Jobs

object DataMapper {
    fun mapResponseToEntities(input: List<DataItem>): List<JobEntity> {
        val jobs = ArrayList<JobEntity>()
        input.map {
            jobs.add(
                JobEntity(
                    jobType = it.jobType,
                    companySlug = it.companySlug,
                    offersRemoteWork = it.offersRemoteWork,
                    companyLogo = it.companyLogo,
                    companyName = it.companyName,
                    createdAt = it.createdAt,
                    location = it.location,
                    id = it.id,
                    title = it.title,
                    expirationDate = it.expirationDate,
                    slug = it.slug,
                    minimumTalentExperience = it.minimumTalentExperience,
                    isFavourite = false
                )
            )
        }
        return jobs
    }

    fun mapResponseByIdToDomain(input: Data): Jobs {
        return Jobs(
            jobType = input.jobType,
            companySlug = input.companySlug,
            offersRemoteWork = input.offersRemoteWork,
            companyLogo = input.companyLogo,
            companyName = input.companyName,
            createdAt = input.createdAt,
            location = input.location,
            id = input.id,
            title = input.title,
            expirationDate = input.expirationDate,
            slug = input.slug,
            description = input.description,
            minimumTalentExperience = input.minimumTalentExperience,
            isFavourite = false
        )
    }

    fun mapEntitiesToDomain(input: List<JobEntity>): List<Jobs> {
        val jobs = ArrayList<Jobs>()
        input.map {
            jobs.add(
                Jobs(
                    jobType = it.jobType,
                    companySlug = it.companySlug,
                    offersRemoteWork = it.offersRemoteWork,
                    companyLogo = it.companyLogo,
                    companyName = it.companyName,
                    createdAt = it.createdAt,
                    location = it.location,
                    id = it.id,
                    title = it.title,
                    expirationDate = it.expirationDate,
                    slug = it.slug,
                    minimumTalentExperience = it.minimumTalentExperience,
                    isFavourite = false
                )
            )
        }
        return jobs
    }

    fun mapDomainToEntities(input: Jobs): JobEntity {
        return JobEntity(
            jobType = input.jobType,
            companySlug = input.companySlug,
            offersRemoteWork = input.offersRemoteWork,
            companyLogo = input.companyLogo,
            companyName = input.companyName,
            createdAt = input.createdAt,
            location = input.location,
            id = input.id,
            title = input.title,
            expirationDate = input.expirationDate,
            slug = input.slug,
            minimumTalentExperience = input.minimumTalentExperience,
            isFavourite = false
        )
    }
}