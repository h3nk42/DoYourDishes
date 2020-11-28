package com.view.gui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.view.R;

import net.sharksystem.asap.android.apps.ASAPApplication;

import java.util.Collection;
import java.util.Set;

public class BluetoothActivity extends ASAPRootActivity{

    //TODO ASAP impl. un/oder impl. auslagern in Bluetooth class


    public BluetoothActivity(ASAPApplication asapApplication) {
        super(asapApplication);
    }

    private static final int REQUEST_ENABLE_BT = 0;

    private static final int REQUEST_DISCOVER_BT = 1;

    TextView mStatusBlueTv, mPairedTv;
    ImageView mBlueIv;

    Button mOnBtn, mOffBtn, mDiscoverBtn, mPairedBtn;

    BluetoothAdapter mBlueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    mStatusBlueTv   = findViewById(R.id.statusBluetoothTv);
    mPairedTv       = findViewById(R.id.pairedTv);
    mBlueIv         = findViewById(R.id.bluetoothIv);
    mOnBtn          = findViewById(R.id.onBtn);
    mOffBtn         = findViewById(R.id.offBtn);
    mDiscoverBtn    = findViewById(R.id.discoverableBtn);
    mPairedBtn      = findViewById(R.id.pairedBtn);

    //adapter
    mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

    //check if bluetooth is available or not
        if(mBlueAdapter == null){
        mStatusBlueTv.setText("Bluetooth is not available");
    }
        else{
        mStatusBlueTv.setText("Bluetooth is available");
    }

    // set image according to bluetooth status (on/off)
        if(mBlueAdapter.isEnabled()){
        mBlueIv.setImageResource(R.drawable.ic_action_on);
    }else{
        mBlueIv.setImageResource(R.drawable.ic_action_off);
    }

    //on btn click
        mOnBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mBlueAdapter.isEnabled()){
                showToast("Turning On Bluetooth...");
                //intent to on bluetooth
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_ENABLE_BT);
            }else{
                showToast("Bluetooth is already on");
            }
        }
    });

        mDiscoverBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mBlueAdapter.isDiscovering()){
                showToast("Making Your Device Discoverable");
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(intent, REQUEST_DISCOVER_BT);
            }
        }
    });

    //off btn click
        mOffBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mBlueAdapter.isEnabled()){
                mBlueAdapter.disable();
                showToast("Turning Bluetooth off");
                mBlueIv.setImageResource(R.drawable.ic_action_off);
            }else{
                showToast("Bluetoot is already off");
            }
        }
    });

    //discover bluetooth btn
        mPairedBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mBlueAdapter.isEnabled()) {
                mPairedTv.setText("Paired Devices");
                Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
                for (BluetoothDevice device : devices) {
                    mPairedTv.append("\nDevice " + device.getName() + "," + device);
                }
            }else{
                //bluetooth is off so can´t get paired devices
                showToast("Turn on bluetooth to get paired devices");

            }
        }
    });



}

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if(resultCode == RESULT_OK){
                    //bluetooth is on
                    mBlueIv.setImageResource(R.drawable.ic_action_on);
                    showToast("Bluetooth is on");
                }else {
                    //user denied to turn bluetooth on
                    showToast("couldn't on bluetooth");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // toast message function
    private void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }



}
