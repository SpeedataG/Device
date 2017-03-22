package com.speedata.ui.adapter;

import android.util.SparseArray;
import android.view.View;

public class SimpleViewHolder {
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View convertView, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            convertView.setTag(viewHolder);
        }
        View child = viewHolder.get(id);

        if (child == null) {
            child = convertView.findViewById(id);
            viewHolder.put(id, child);
        }
        return (T) child;
    }
}
