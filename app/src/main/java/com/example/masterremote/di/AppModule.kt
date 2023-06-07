package com.example.masterremote.di

import com.example.masterremote.presentation.viewModel.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AppViewModel() }
}