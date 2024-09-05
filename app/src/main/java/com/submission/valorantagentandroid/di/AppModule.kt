package com.submission.valorantagentandroid.di

import com.submission.valorantagentandroid.core.domain.usecase.agent.AgentInteractor
import com.submission.valorantagentandroid.core.domain.usecase.agent.AgentUseCase
import com.submission.valorantagentandroid.core.domain.usecase.news.NewsInteractor
import com.submission.valorantagentandroid.core.domain.usecase.news.NewsUseCase
import com.submission.valorantagentandroid.core.domain.usecase.pref.SettingInteractor
import com.submission.valorantagentandroid.core.domain.usecase.pref.SettingUseCase
import com.submission.valorantagentandroid.presentation.agent.AgentViewModel
import com.submission.valorantagentandroid.presentation.detail.DetailAgentViewModel
import com.submission.valorantagentandroid.presentation.home.HomeViewModel
import com.submission.valorantagentandroid.presentation.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AgentUseCase> { AgentInteractor(get()) }
    factory<NewsUseCase> { NewsInteractor(get()) }
    factory<SettingUseCase> { SettingInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailAgentViewModel(get(), get()) }
    viewModel { AgentViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}

