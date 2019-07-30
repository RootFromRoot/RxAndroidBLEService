package com.vito.bletool.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.bluetooth.BluetoothDevice
import com.polidea.rxandroidble2.RxBleScanResult
import com.vito.bletool.data.service.BluetoothService
import com.vito.bletool.data.service.BluetoothServiceContract
import com.vito.bletool.ui.view.MainView

class MainViewModel(private val bleService: BluetoothServiceContract, application: Application) :
    AndroidViewModel(application) {

    lateinit var view: MainView
    fun bind(view: MainView) {
        this.view = view
    }

    fun findDevices() {
        bleService.attachOnScanDevicesListener(object : BluetoothService.OnDevicesScanning {
            override fun onDevicesScanningFinished(device: RxBleScanResult) {
            }
        })
        bleService.findDevices()
    }

    fun isInBondedDevices(): Boolean {
        return false
    }

}