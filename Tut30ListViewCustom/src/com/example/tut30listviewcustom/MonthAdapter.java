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

	LayoutInflater layoutInflater;
	String[] months;
	
	int [] seasonIconRscIds = { R.drawable.winter, R.drawable.spring, R.drawable.summer, R.drawable.fall }  ;

	public MonthAdapter(Context context, String[] objects) {
		super(context, R.layout.month_item_view, objects);

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
		
		int seasonIndex = position/4;
		imageView.setImageResource( seasonIconRscIds[ seasonIndex ] );

		if( position == 1 ) { 
			radioButton.setChecked( true );
		}
		
		if (position < months.length) {
			textView.setText(months[position]);
		}

		return itemView;
	}

}
