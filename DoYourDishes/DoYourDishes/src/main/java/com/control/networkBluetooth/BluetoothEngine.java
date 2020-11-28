package com.control.networkBluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;

public interface BluetoothEngine {

    BroadcastReceiver getBluetoothDeviceReceiver();

    void discoverDevices(Activity activity);

    void accept();

    BluetoothDevice connect();

}
