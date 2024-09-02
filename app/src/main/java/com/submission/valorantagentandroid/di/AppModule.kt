package com.submission.valorantagentandroid.di

import com.submission.valorantagentandroid.core.domain.usecase.AgentInteractor
import com.submission.valorantagentandroid.core.domain.usecase.AgentUseCase
import com.submission.valorantagentandroid.presentation.detail.DetailAgentViewModel
import com.submission.valorantagentandroid.presentation.favorite.FavoriteViewModel
import com.submission.valorantagentandroid.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AgentUseCase> { AgentInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailAgentViewModel(get()) }
}
