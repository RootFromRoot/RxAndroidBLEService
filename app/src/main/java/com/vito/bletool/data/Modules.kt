package com.vito.bletool.data

import com.vito.bletool.data.service.BluetoothService
import com.vito.bletool.data.service.BluetoothServiceContract
import com.vito.bletool.ui.viewmodel.BleViewModel
import com.vito.bletool.ui.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module(override = true) {
    single<BluetoothServiceContract> { BluetoothService(androidContext()) }
    viewModel { BleViewModel(get(), androidApplication()) }
    viewModel { MainViewModel(get(), androidApplication()) }
}