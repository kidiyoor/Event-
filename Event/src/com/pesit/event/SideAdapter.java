package com.pesit.event;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class SideAdapter extends BaseAdapter {

	 Context context;
	 List<SideItem> rowItem;

	 SideAdapter(Context context, List<SideItem> rowItem) {
	  this.context = context;
	  this.rowItem = rowItem;
	 }


	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {

	if (convertView == null) {
	            LayoutInflater mInflater = (LayoutInflater) context
	                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	            convertView = mInflater.inflate(R.layout.item_side, null);
	        }

	        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

	        SideItem row_pos = rowItem.get(position);
	        // setting the image resource and title
	        txtTitle.setText(row_pos.getTitle());

	        return convertView;
	 }

	 @Override
	 public int getCount() {
	  return rowItem.size();
	 }

	 @Override
	 public Object getItem(int position) {
	  return rowItem.get(position);
	 }

	 @Override
	 public long getItemId(int position) {
	  return rowItem.indexOf(getItem(position));
	 }

	}