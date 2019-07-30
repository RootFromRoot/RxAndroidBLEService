package com.vito.bletool.data.service

import android.annotation.SuppressLint
import android.content.Context
import com.polidea.rxandroidble2.RxBleClient
import com.polidea.rxandroidble2.RxBleScanResult
import com.vito.bletool.data.MAC
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.*


class BluetoothService(context: Context) : BluetoothServiceContract {

    private val rxBleClient = RxBleClient.create(context)
    private val connection = rxBleClient.getBleDevice(MAC).establishConnection(false)

    private lateinit var onScanDevicesListener: OnDevicesScanning
    private lateinit var onDeviceConnectingListener: OnDeviceConnecting
    private lateinit var onReadingCharacteristicListener: OnCharacteristicReading
    private lateinit var onWritingCharacteristicListener: OnCharacteristicWriting
    private lateinit var onChangingCharacteristicListener: OnCharacteristicsChanging

    interface OnDevicesScanning {
        fun onDevicesScanningFinished(device: RxBleScanResult)
    }

    interface OnDeviceConnecting {
        fun onDeviceConnected(isConnected: Boolean)
    }

    interface OnCharacteristicReading {
        fun onCharacteristicRead(characteristicValue: ByteArray, isSuccessfully: Boolean)
    }

    interface OnCharacteristicWriting {
        fun onCharacteristicWrite(characteristicValue: ByteArray, isSuccessfully: Boolean)
    }

    interface OnCharacteristicsChanging {
        fun onCharacteristicsChanged(characteristicValue: ByteArray, isSuccessfully: Boolean)
    }

    override fun attachOnScanDevicesListener(listener: OnDevicesScanning) {
        this.onScanDevicesListener = listener
    }

    override fun attachOnDeviceConnectingListener(listener: OnDeviceConnecting) {
        this.onDeviceConnectingListener = listener
    }

    override fun attachOnReadingCharacteristicListener(listener: OnCharacteristicReading) {
        this.onReadingCharacteristicListener = listener
    }

    override fun attachOnWritingCharacteristicListener(listener: OnCharacteristicWriting) {
        this.onWritingCharacteristicListener = listener
    }

    override fun attachOnCharacteristicsChangingListener(listener: OnCharacteristicsChanging) {
        this.onChangingCharacteristicListener = listener
    }

    @SuppressLint("CheckResult")
    override fun findDevices() {
        rxBleClient.scanBleDevices()
            .subscribe({ scanResult -> onScanDevicesListener.onDevicesScanningFinished(scanResult) },
                {
                    val error = it
                    Timber.i(error.toString())
                })
    }

    @SuppressLint("CheckResult")
    override fun connectToDevice(autoConnect: Boolean): Disposable {
        return connection
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onDeviceConnectingListener.onDeviceConnected(true)
                },
                {
                    val error = it.toString()
                    Timber.i(error)
                    onDeviceConnectingListener.onDeviceConnected(false)
                })
    }

    @SuppressLint("CheckResult")
    override fun readCharacteristic(autoConnect: Boolean, characteristicUUID: UUID): Disposable {

        return connection
            .flatMapSingle { it.readCharacteristic(characteristicUUID) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { characteristicValue ->
                    onReadingCharacteristicListener.onCharacteristicRead(
                        characteristicValue,
                        true
                    )
                },
                {
                    val error = it.toString()
                    Timber.i(error)
                    onReadingCharacteristicListener.onCharacteristicRead(ByteArray(0), false)
                }
            )
    }

    @SuppressLint("CheckResult")
    override fun writeCharacteristicInBytes(
        autoConnect: Boolean,
        characteristicUUID: UUID,
        bytesToWrite: ByteArray
    ): Disposable {
        return connection
            .flatMapSingle { rxBleConnection ->
                rxBleConnection.writeCharacteristic(characteristicUUID, bytesToWrite)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ characteristicValue ->
                onWritingCharacteristicListener.onCharacteristicWrite(characteristicValue, true)
            },
                {
                    Timber.i(it.toString())
                    onWritingCharacteristicListener.onCharacteristicWrite(ByteArray(0), false)
                })
    }

    @SuppressLint("CheckResult")
    override fun makeNotification(autoConnect: Boolean, characteristicUUID: UUID): Disposable {
        return connection
            .flatMap { rxBleConnection -> rxBleConnection.setupNotification(characteristicUUID) }
            .flatMap { notificationObservable -> notificationObservable }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ bytes ->
                onChangingCharacteristicListener.onCharacteristicsChanged(bytes, true)
            }, {
                onChangingCharacteristicListener.onCharacteristicsChanged(ByteArray(0), false)
            }
            )
    }

    override fun unsubscribe(disposable: Disposable) {
        disposable.dispose()
    }

}