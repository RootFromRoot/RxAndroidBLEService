package com.vito.bletool.data.service

import io.reactivex.disposables.Disposable
import java.util.*


interface BluetoothServiceContract {

    fun attachOnScanDevicesListener(listener: BluetoothService.OnDevicesScanning)
    fun attachOnDeviceConnectingListener(listener: BluetoothService.OnDeviceConnecting)
    fun attachOnReadingCharacteristicListener(listener: BluetoothService.OnCharacteristicReading)
    fun attachOnWritingCharacteristicListener(listener: BluetoothService.OnCharacteristicWriting)
    fun attachOnCharacteristicsChangingListener(listener: BluetoothService.OnCharacteristicsChanging)

    fun findDevices()
    fun connectToDevice(autoConnect: Boolean): Disposable
    fun readCharacteristic(autoConnect: Boolean, characteristicUUID: UUID): Disposable
    fun writeCharacteristicInBytes(autoConnect: Boolean, characteristicUUID: UUID, bytesToWrite: ByteArray): Disposable
    fun makeNotification(autoConnect: Boolean, characteristicUUID: UUID): Disposable
    fun unsubscribe(disposable: Disposable)
}