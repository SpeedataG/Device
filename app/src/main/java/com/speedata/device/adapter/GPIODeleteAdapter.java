package com.speedata.device.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.speedata.device.R;

import java.util.List;

/**
 * @author Jason
 */
public class GPIODeleteAdapter extends BaseAdapter {

    private List<Integer> list;
    private Context context;

    public GPIODeleteAdapter(Context context, @Nullable List<Integer> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list == null)
            return 0;
        else
            return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_serial_port, null);
            holder.name = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position) + "");
        return convertView;
    }

    class ViewHolder {
        TextView name;
    }

}