package com.submission.favorite.presentation

import androidx.lifecycle.ViewModel
import com.submission.core.domain.usecase.JobUseCase

class FavouriteViewModel (jobUseCase: JobUseCase): ViewModel() {
   val favJob = jobUseCase.getFavJob()
}