package com.example.zhongyu.retrofitdemo.Bluetooth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.ArrayList;
import java.util.HashMap;

import de.greenrobot.event.EventBus;

/**
 * Created by zhongyu on 1/7/2016.
 */
public class BluetoothService {
    private static final String TAG = "BluetoothService";

    private Context context = null;
    private ArrayList<BluetoothDevice> unbondDevices = null; // 用于存放未配对蓝牙设备
    private ArrayList<BluetoothDevice> bondDevices = null;// 用于存放已配对蓝牙设备
    private RecyclerView unbondDevicesListRecycleView = null;
    private BluetoothAdapter bluetoolthAdapter;
    public BluetoothService(Context context, RecyclerView unbondDevicesRecycleView, BluetoolthAdapter bluetoolthAdapter) {
        this.context = context;
        this.unbondDevicesListRecycleView = unbondDevicesRecycleView;
        this.unbondDevices = new ArrayList<BluetoothDevice>();
        this.bluetoolthAdapter = BluetoothAdapter.getDefaultAdapter();
        this.initIntentFilter();
    }

    public BluetoothService(Context context) {
        this.context = context;
        this.initIntentFilter();
        this.bluetoolthAdapter = BluetoothAdapter.getDefaultAdapter();
        EventBus.getDefault().register(context);
    }

    private void initIntentFilter() {
        // 设置广播信息过滤
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        // 注册广播接收器，接收并处理搜索结果
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * 添加已绑定蓝牙设备到ListView
     */
    private void addBondDevicesToListView() {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        int count = this.bondDevices.size();
        for (int i = 0; i < count; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("deviceName", this.bondDevices.get(i).getName());
            data.add(map);// 把item项的数据加到data中
        }
    }

    /**
     * 添加未绑定蓝牙设备到ListView
     */
    private void addUnbondDevicesToListView() {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        int count = this.unbondDevices.size();
        for (int i = 0; i < count; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("deviceName", this.unbondDevices.get(i).getName());
            data.add(map);// 把item项的数据加到data中
        }
    }

    /**
     * 打开蓝牙
     */
    public void openBluetooth(Activity activity) {
        Intent enableBtIntent = new Intent(
                BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBtIntent, 1);
    }

    /**
     * 关闭蓝牙
     */
    public void closeBluetooth() {
        this.bluetoolthAdapter.disable();
    }

    /**
     * 判断蓝牙是否打开
     *
     * @return boolean
     */
    public boolean isOpen() {
        return this.bluetoolthAdapter.isEnabled();
    }

    /*
    * 搜索蓝牙设备
    * */
    public void searchDevices() {
        EventBus.getDefault().post(new BlueScanEvent(true));
        // 寻找蓝牙设备，android会将查找到的设备以广播形式发出去
        this.bluetoolthAdapter.startDiscovery();
    }

    /**
     * 蓝牙广播接收器
     */
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        ProgressDialog progressDialog = null;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive() returned: " + action);
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                    addDevies(bluetoothDevice);
                }else {
                    addDevies(bluetoothDevice);
                }
            }else if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
//                progressDialog = ProgressDialog.show(context, "请稍等...",
//                        "搜索蓝牙设备中...", true);
            }else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
//                progressDialog.dismiss();
                bluetoolthAdapter = null;
                EventBus.getDefault().post(new BlueScanEvent(false));
            }

            if(BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                if(bluetoolthAdapter.getState() == BluetoothAdapter.STATE_ON) {
                    /*
                    * 关闭蓝牙
                    * */
                }else if(bluetoolthAdapter.getState() == BluetoothAdapter.STATE_OFF) {
                    /*
                    * 打开蓝牙
                    * */
                }
            }
        }
    };

    private void addDevies(BluetoothDevice bluetoothDevice) {
//        if(!this.unbondDevices.contains(bluetoothDevice)) {
            EventBus.getDefault().post(new BluetoothEvent(bluetoothDevice));
//        }
    }
}