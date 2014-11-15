package com.pesit.event;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter {
	 
	private ArrayList listData;
 
	private LayoutInflater layoutInflater;
 
	public EventAdapter(Context context, ArrayList listData) {
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
	}
 
	@Override
	public int getCount() {
		return listData.size();
	}
 
	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}
 
	@Override
	public long getItemId(int position) {
		return position;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.listview_item_row, null);
			holder = new ViewHolder();
			holder.headlineView = (TextView) convertView.findViewById(R.id.title);
			holder.reporterNameView = (TextView) convertView.findViewById(R.id.reporter);
			holder.reportedDateView = (TextView) convertView.findViewById(R.id.date);
			holder.imageView = (ImageView) convertView.findViewById(R.id.thumbImage);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
 
		Event Item = (Event) listData.get(position);
 
		holder.headlineView.setText(Item.title);
		holder.reporterNameView.setText(Item.org);
		holder.reportedDateView.setText(Item.date);
 
		if (holder.imageView != null) {
			new ImageDownloaderTask(holder.imageView).execute(Item.icon);
		}
 
		return convertView;
	}
 
	static class ViewHolder {
		TextView headlineView;
		TextView reporterNameView;
		TextView reportedDateView;
		ImageView imageView;
	}
}