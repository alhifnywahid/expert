package com.submission.favorite.di

import com.submission.favorite.presentation.FavouriteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
   viewModel { FavouriteViewModel(get()) }
}