package com.krikelin.nlt;

import java.util.ArrayList;
import java.util.List;

import nl.matshofman.saxrssreader.RssItem;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsListAdapter extends ArrayAdapter<RssItem> {
	List<RssItem> items = new ArrayList<RssItem>();
	public NewsListAdapter(Context context, int resource, List<RssItem> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		items = objects;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}
	@Override
	public RssItem getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		RssItem item = getItem(position);
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.news_item, null);
			
		}
		TextView tv1 = (TextView)convertView.findViewById(android.R.id.text1);
		tv1.setText(item.getTitle());
		TextView tv2 = (TextView)convertView.findViewById(android.R.id.text2);
		tv2.setText(item.getPubDate().toLocaleString());
		TextView tv3 = (TextView)convertView.findViewById(R.id.text);
		tv3.setText(item.getDescription());
		return convertView;
	}
	

}
