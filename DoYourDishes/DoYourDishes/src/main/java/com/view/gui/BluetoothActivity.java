package com.view.gui;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.view.R;

public class BluetoothActivity extends AppCompatActivity {

    TextView returnedString;
    ImageView bluetoothIv;
    Button connectButton;

    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;
    BluetoothLeScanner bluetoothLeScanner;


    private String getAccessKeyFromEsp(){

        String key = null;

        // Bluetooth Service
        bluetoothManager =(BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

        // adapter initialization --> Default Adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //bluetoothLeScanner = BluetoothLeScanner





        return key;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
