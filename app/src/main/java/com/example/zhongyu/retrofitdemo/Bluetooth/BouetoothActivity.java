package com.example.zhongyu.retrofitdemo.Bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.zhongyu.retrofitdemo.R;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhongyu on 1/7/2016.
 */
public class BouetoothActivity extends Activity{

    RecyclerView bluRecyclerView;
    BluetoolthAdapter bluetoolthAdapter;
    private List<BluetoothDevice> deviceList;
    private BluetoothService bluetoothService;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        init();


        bluetoothService = new BluetoothService(this);

        if(!bluetoothService.isOpen()) {
            bluetoothService.openBluetooth(this);
        }

        bluetoothService.searchDevices();
    }



    private void init() {
        initDatas();
        initViews();
    }

    public void initDatas(){
        deviceList = new ArrayList<>();
    }

    private void initViews() {
        bluRecyclerView = (RecyclerView) findViewById(R.id.blu_recycleview);
        bluRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.blue_scan_progress);
        bluetoolthAdapter = new BluetoolthAdapter(this, deviceList);
        bluetoolthAdapter.setOnItemClickListener(new BluetoolthAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                try {
                    Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
                    try {
                        createBondMethod.invoke(deviceList.get(position));
                        blueConnect(deviceList.get(position));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
        bluRecyclerView.setAdapter(bluetoolthAdapter);
    }

    public void onEventMainThread(BluetoothEvent bluetoothEvent) {

        if(bluetoothEvent.getShowProgress() != null) {
            if(bluetoothEvent.getShowProgress()) {
                progressBar.setVisibility(View.VISIBLE);
            }else {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }

        if(bluetoothEvent.getBluetoothDevice() != null) {
            deviceList.add(bluetoothEvent.getBluetoothDevice());
            bluetoolthAdapter.notifyDataSetChanged();
        }
    }

    public void blueConnect(BluetoothDevice bluetoothDevice) {
        // 固定的UUID
        final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        UUID uuid = UUID.fromString(SPP_UUID);
        try {
            BluetoothSocket bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
            bluetoothSocket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

















