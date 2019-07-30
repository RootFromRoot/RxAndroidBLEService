package com.vito.bletool.data

/**
 * Services and related characteristics ids.
 * Characteristic names has following postfixes at the name:
 * _RW - means that characteristic expected to support Read and Write operations
 * _N - means that characteristic changes can be observed by Notification operation.
 */

const val SERVICE_ID = "000fff0-0000-1000-8000-00805f9b34fb"
const val CHARACTERISTIC_ID_1_RW = "000fff1-0000-1000-8000-00805f9b34fb"
const val CHARACTERISTIC_ID_2_N = "000fff2-0000-1000-8000-00805f9b34fb"
const val CHARACTERISTIC_ID_3_RW = "000fff3-0000-1000-8000-00805f9b34fb"
const val CHARACTERISTIC_ID_4_N = "000fff4-0000-1000-8000-00805f9b34fb"

const val MAC = "76:9E:30:29:D0:85"
const val TEST_CHARACTERSTIC_1 = "00002a00-0000-1000-8000-00805f9b34fb"
const val TEST_MAC = "45:A2:CF:7F:24:D8"
