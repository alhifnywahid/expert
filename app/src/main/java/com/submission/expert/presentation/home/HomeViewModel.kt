package com.submission.expert.presentation.home

import androidx.lifecycle.ViewModel
import com.submission.core.domain.usecase.JobUseCase

class HomeViewModel(jobUseCase: JobUseCase) : ViewModel() {
    val jobList = jobUseCase.getJobs()
}