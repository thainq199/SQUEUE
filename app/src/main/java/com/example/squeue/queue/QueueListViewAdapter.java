package com.example.squeue.queue;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.squeue.R;
import com.example.squeue.model.Address;

import java.util.ArrayList;

public class QueueListViewAdapter extends BaseAdapter {
    private ArrayList<Address> listQueue;

    public QueueListViewAdapter(ArrayList<Address> listQueue) {
        this.listQueue = listQueue;
    }

    @Override
    public int getCount() {
        return listQueue.size();
    }

    @Override
    public Object getItem(int position) {
        return listQueue.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listQueue.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        ImageView ivStart, ivDelete;

        View viewAddress;
        if (convertView == null) {
            viewAddress = View.inflate(parent.getContext(), R.layout.listview_queue, null);
        } else viewAddress = convertView;

        //Bind sữ liệu phần tử vào View
        Address address = (Address) getItem(position);
        ((TextView) viewAddress.findViewById(R.id.idAddress)).setText(String.format("ID = %d", address.getId()));
        ((TextView) viewAddress.findViewById(R.id.nameCity)).setText(address.getCity());
        ivStart = (ImageView) viewAddress.findViewById(R.id.ivStart);
        ivDelete = (ImageView) viewAddress.findViewById(R.id.ivDelete);

       ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return viewAddress;
    }
}
