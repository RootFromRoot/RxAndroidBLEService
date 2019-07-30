package com.vito.bletool.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.vito.bletool.R
import com.vito.bletool.data.*
import com.vito.bletool.ui.view.BleView
import com.vito.bletool.ui.viewmodel.BleViewModel
import kotlinx.android.synthetic.main.activity_ble.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.nio.charset.Charset

class BleActivity : AppCompatActivity(), BleView {

    private val viewModel: BleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        viewModel.bind(this)
    }

    private fun setupView() {
        setContentView(R.layout.activity_ble)

        btnReadChar1.setOnClickListener {
            viewModel.readCharacteristicsFirstType(CHARACTERISTIC_ID_1_RW)
        }
        btnNotifyChar2.setOnClickListener {
            viewModel.notifyCharacteristicsSecondType(CHARACTERISTIC_ID_2_N)
        }
        btnReadChar3.setOnClickListener {
            viewModel.readCharacteristicsThirdType(CHARACTERISTIC_ID_3_RW)
        }
        btnNotifyChar4.setOnClickListener {
            viewModel.notifyCharacteristicsFourthType(CHARACTERISTIC_ID_4_N)
        }
        btnWriteChar3.setOnClickListener {
            viewModel.writeCharacteristicsThirdType(
                CHARACTERISTIC_ID_3_RW,
                input.text.toString().toByteArray(Charset.defaultCharset())
            )
        }
        bthWriteChar1.setOnClickListener {
            viewModel.writeCharacteristicsFirstType(
                CHARACTERISTIC_ID_1_RW,
                input.text.toString().toByteArray(Charset.defaultCharset())
            )
        }
    }

    override fun setTextToTextView1(text: String) {
        tvChar1.text = text
    }

    override fun setTextToTextView2(text: String) {
        tvChar1.text = text
    }

    override fun setTextToTextView3(text: String) {
        tvChar1.text = text
    }

    override fun setTextToTextView4(text: String) {
        tvChar1.text = text
    }

    override fun showToast() {
        Toast.makeText(this, "Cant complete operation!", Toast.LENGTH_SHORT).show()
    }

    override fun showSuccessToast() {
        Toast.makeText(this, "Successfully", Toast.LENGTH_SHORT).show()
    }

}
