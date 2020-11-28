package com.control.networkBluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;

public class Bluetooth implements BluetoothInterface {

    @Override
    public BroadcastReceiver getBluetoothDeviceReceiver() {
        return null;
    }

    @Override
    public void discoverDevices(Activity activity) {

    }

    @Override
    public void accept() {

    }

    @Override
    public BluetoothDevice connect() {
        return null;
    }
}
