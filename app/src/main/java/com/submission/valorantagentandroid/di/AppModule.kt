package com.submission.valorantagentandroid.di

import com.submission.valorantagentandroid.core.domain.usecase.agent.AgentInteractor
import com.submission.valorantagentandroid.core.domain.usecase.agent.AgentUseCase
import com.submission.valorantagentandroid.core.domain.usecase.news.NewsInteractor
import com.submission.valorantagentandroid.core.domain.usecase.news.NewsUseCase
import com.submission.valorantagentandroid.presentation.agent.AgentViewModel
import com.submission.valorantagentandroid.presentation.detail.DetailAgentViewModel
import com.submission.valorantagentandroid.presentation.favorite.FavoriteViewModel
import com.submission.valorantagentandroid.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AgentUseCase> { AgentInteractor(get()) }
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailAgentViewModel(get()) }
    viewModel { AgentViewModel(get()) }
}

