package com.example.skinimaging;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.content.Intent;

public class AnimatePictures extends Activity {

	private Integer gifLocation = R.drawable.skingif1; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animate_pictures);
		//setContentView(setGifAnimation((GridView) findViewById(R.id.gridview)));
		//GIFView gifView = new GIFView(this);
	    //gifView.setGIFResource(R.drawable.skingif1);
		//setContentView(gifView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.animate_pictures, menu);
		return true;
	}
	
	public View setGifAnimation(View convertView) {
		ImageView imageView; 
		if (convertView == null) {
			imageView = new ImageView(this);
			imageView.setLayoutParams(new GridView.LayoutParams(200,200));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(10,10,10,10);
		} else {
			imageView = (ImageView) convertView;
		}
		
		imageView.setImageResource(gifLocation);
		return imageView;
	}

}
