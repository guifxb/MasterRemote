package com.example.masterremote.core.di

import com.example.masterremote.core.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AppViewModel() }
}