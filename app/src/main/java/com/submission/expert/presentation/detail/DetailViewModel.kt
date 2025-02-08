package com.submission.expert.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.submission.core.data.source.remote.Resource
import com.submission.core.domain.model.Jobs
import com.submission.core.domain.usecase.JobUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val jobUseCase: JobUseCase) : ViewModel() {

    private var _jobData = MutableStateFlow<Resource<Jobs>?>(null)
    val jobData: StateFlow<Resource<Jobs>?> = _jobData

    val favoriteJob = jobUseCase.getFavJob()

    fun getJob(id: Int) {

        viewModelScope.launch {
            jobUseCase.getDetailJob(id).collect { resource ->
                _jobData.value = resource
            }
        }
    }

    fun setFavJob(job: Jobs, status: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            jobUseCase.setFavJob(job, status)
        }
    }
}