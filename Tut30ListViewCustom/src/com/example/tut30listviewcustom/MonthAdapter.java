package com.example.tut30listviewcustom;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MonthAdapter extends ArrayAdapter<String> {

	Context context; 
	LayoutInflater layoutInflater;
	String[] months;

	public MonthAdapter(Context context, String[] objects) {
		super(context, R.layout.month_item_view, objects);

		this.context = context;
		this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.months = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View itemView;

		if (convertView == null) { 
			itemView = layoutInflater.inflate( R.layout.month_item_view, parent, false);
		} else {
			itemView = convertView;
		}

		ImageView imageView = (ImageView) itemView.findViewById(R.id.month_item_icon);
		RadioButton radioButton = (RadioButton) itemView.findViewById(R.id.month_item_checked);
		TextView textView = (TextView) itemView.findViewById(R.id.month_item_text);

		radioButton.setChecked(position == 1);
		if (position < months.length) {
			textView.setText(months[position]);
		}

		return itemView;
	}

}
