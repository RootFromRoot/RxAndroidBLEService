package com.vito.bletool.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.vito.bletool.data.service.BluetoothService
import com.vito.bletool.data.service.BluetoothServiceContract
import com.vito.bletool.ui.view.BleView
import java.util.*

class BleViewModel(private val bleService: BluetoothServiceContract, application: Application) :
    AndroidViewModel(application) {

    lateinit var view: BleView

    fun bind(view: BleView) {
        this.view = view
    }

    fun readCharacteristicsThirdType(characteristicType: String) {
        bleService.attachOnReadingCharacteristicListener(object : BluetoothService.OnCharacteristicReading {
            override fun onCharacteristicRead(characteristicValue: ByteArray, isSuccessfully: Boolean) {
                if (isSuccessfully) {
                    view.setTextToTextView3(characteristicValue.toString())
                    bleService.readCharacteristic(false, UUID.fromString(characteristicType)).dispose()
                } else view.showToast()
            }
        })
        bleService.readCharacteristic(false, UUID.fromString(characteristicType))
    }

    fun readCharacteristicsFirstType(characteristicType: String) {
        bleService.attachOnReadingCharacteristicListener(object : BluetoothService.OnCharacteristicReading {
            override fun onCharacteristicRead(characteristicValue: ByteArray, isSuccessfully: Boolean) {
                if (isSuccessfully) {
                    view.setTextToTextView1(characteristicValue.toString())
                    bleService.readCharacteristic(false, UUID.fromString(characteristicType)).dispose()
                } else view.showToast()
            }
        })
        bleService.readCharacteristic(false, UUID.fromString(characteristicType))
    }

    fun notifyCharacteristicsSecondType(characteristicType: String) {
        bleService.attachOnCharacteristicsChangingListener(object : BluetoothService.OnCharacteristicsChanging {
            override fun onCharacteristicsChanged(characteristicValue: ByteArray, isSuccessfully: Boolean) {
                if (isSuccessfully) {
                    view.setTextToTextView2(characteristicValue.toString())
                    bleService.makeNotification(false, UUID.fromString(characteristicType)).dispose()
                } else view.showToast()
            }
        })
        bleService.makeNotification(false, UUID.fromString(characteristicType))
    }

    fun notifyCharacteristicsFourthType(characteristicType: String) {
        bleService.attachOnCharacteristicsChangingListener(object : BluetoothService.OnCharacteristicsChanging {
            override fun onCharacteristicsChanged(characteristicValue: ByteArray, isSuccessfully: Boolean) {
                if (isSuccessfully) {
                    view.setTextToTextView4(characteristicValue.toString())
                    bleService.makeNotification(false, UUID.fromString(characteristicType)).dispose()
                } else view.showToast()
            }
        })
        bleService.makeNotification(false, UUID.fromString(characteristicType))
    }

    fun writeCharacteristicsFirstType(characteristicType: String, bytesToWrite: ByteArray) {
        bleService.attachOnWritingCharacteristicListener(object : BluetoothService.OnCharacteristicWriting {
            override fun onCharacteristicWrite(characteristicValue: ByteArray, isSuccessfully: Boolean) {
                if (isSuccessfully) {
                    view.showSuccessToast()
                    bleService.writeCharacteristicInBytes(false, UUID.fromString(characteristicType), bytesToWrite)
                        .dispose()
                } else view.showToast()
            }
        })
        bleService.writeCharacteristicInBytes(false, UUID.fromString(characteristicType), bytesToWrite)
    }

    fun writeCharacteristicsThirdType(characteristicType: String, bytesToWrite: ByteArray) {
        bleService.attachOnWritingCharacteristicListener(object : BluetoothService.OnCharacteristicWriting {
            override fun onCharacteristicWrite(characteristicValue: ByteArray, isSuccessfully: Boolean) {
                if (isSuccessfully) {
                    view.showSuccessToast()
                    bleService.writeCharacteristicInBytes(false, UUID.fromString(characteristicType), bytesToWrite)
                        .dispose()
                } else view.showToast()
            }
        })
        bleService.writeCharacteristicInBytes(false, UUID.fromString(characteristicType), bytesToWrite)
    }

}