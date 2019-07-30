package com.vito.bletool.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vito.bletool.R
import com.vito.bletool.data.MAC
import com.vito.bletool.data.TEST_MAC
import com.vito.bletool.ui.view.MainView
import com.vito.bletool.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


/**
 * Initial activituy that should start pairing process
 * for first time.
 * If its not first time, user should wait until
 */

class MainActivity : AppCompatActivity(), MainView {
    override fun showToast() {
        Toast.makeText(this, "Can`t connect", Toast.LENGTH_SHORT).show()
    }

    private val mViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel.bind(this)

        button.setOnClickListener {
            mViewModel.findDevices()
            goToBleActivity()
        }
    }

    private fun startBleScan() {
        // TODO If this device is in Bonded devices than try to connect to it right away
        // show alert if not succeed
        // If device not in Bonded devices than start scan for BLE Devices for 15 seconds in order to find specific device
        // If device not found show alert.
        // If device found, connect to it and go to BleActivity

        if (mViewModel.isInBondedDevices()) {
           //  mViewModel.connect(TEST_MAC)
        } else {
            mViewModel.findDevices()
        }
    }


    private fun onConnectionToDeviceSucced() {
        Timber.e("Connected to Ble Device.")
        goToBleActivity()
    }

    private fun onConnectionToDeviceFailed() {
        Timber.e("Failed to connect to Ble Device.")
    }

    private fun onBleConnectionLost() {
        Timber.e("Ble connection Lost!")
    }

    override fun goToBleActivity() {
        startActivity(Intent(this, BleActivity::class.java))
    }
}
