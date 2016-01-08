package com.example.zhongyu.retrofitdemo.Bluetooth;

import android.bluetooth.BluetoothDevice;

/**
 * Created by zhongyu on 1/7/2016.
 */
public class BluetoothEvent {

    private BluetoothDevice bluetoothDevice;

    public Boolean getShowProgress() {
        return showProgress;
    }

    private Boolean showProgress;

    public BluetoothEvent(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;

    }

    public BluetoothEvent( Boolean showProgress) {
        this.showProgress = showProgress;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }
}
