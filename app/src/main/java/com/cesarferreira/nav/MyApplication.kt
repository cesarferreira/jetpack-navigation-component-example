package com.cesarferreira.nav

import android.app.Application
import com.cesarferreira.nav.login.LoginViewModel
import com.cesarferreira.nav.main.MainViewModel
import com.cesarferreira.nav.profile.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            logger(AndroidLogger())
            modules(listOf(applicationModule))
            androidContext(this@MyApplication)
        }
    }
}

val applicationModule = module {
    viewModel { LoginViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { MainViewModel() }
}