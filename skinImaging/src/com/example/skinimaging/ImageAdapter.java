package com.example.skinimaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private Integer[] mThumbIds;
	
	public ImageAdapter(Context c) {
		mContext = c;
		mThumbIds = getMThumbIds();
		System.out.println(mThumbIds.toString());
	}
	
	@Override
	public int getCount() {
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View gridView; 
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if (convertView == null) {
			gridView = new View(mContext);
			//imageView.setLayoutParams(new GridView.LayoutParams(200,200));
			//imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			//imageView.setPadding(10,10,10,10);
			gridView = inflater.inflate(R.layout.region_icon, null);
			
			// Set the text to the specified grid item "Region "
			TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
			textView.setText("Region " + Integer.toString(position));
			
			ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
			imageView.setImageResource(mThumbIds[position]);
			
		} else {
			gridView = (View) convertView;
		}
		
		//imageView.setImageResource(mThumbIds[position]);
		return gridView;
	}
	
	// references to the images 
	private Integer[] getMThumbIds() {
		Integer[] integers = new Integer[6];
		//int drawableID = mContext.getResources().getIdentifier("w1.jpg", "drawable",getApplicationContext().getPackageName());
		integers[0] = R.drawable.w0;
		integers[1] = R.drawable.w1;
		integers[2] = R.drawable.w2;
		integers[3] = R.drawable.w3;
		integers[4] = R.drawable.w0;
		integers[5] = R.drawable.w1;
		
		return integers;
	};
}
