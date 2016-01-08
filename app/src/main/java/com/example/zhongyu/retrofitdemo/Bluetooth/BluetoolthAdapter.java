package com.example.zhongyu.retrofitdemo.Bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhongyu.retrofitdemo.R;


import java.util.List;
/**
 * Created by zhongyu on 1/7/2016.
 */
public class BluetoolthAdapter extends RecyclerView.Adapter<BluetoolthAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private List<BluetoothDevice> devicelist;

    /*
    * ItemClick callback
    * */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public BluetoolthAdapter(Context context, List<BluetoothDevice> devicelist) {
        layoutInflater = LayoutInflater.from(context);
        this.devicelist = devicelist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.bluetoolth_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.tvDecvie = (TextView) view.findViewById(R.id.device_name);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvDecvie.setText(devicelist.get(position).getName());

        if(onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return devicelist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDecvie;
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
