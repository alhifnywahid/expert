package com.submission.expert.di

import com.submission.expert.presentation.detail.DetailViewModel
import com.submission.expert.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}