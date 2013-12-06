package com.example.skinimaging;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class AnimatePictures extends Activity {
	
	private Integer gifLocation = R.drawable.skingif1; 
	
	GIFView view;
	Button buttonViewGraph;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animate_pictures);
		
		view = (GIFView) findViewById(R.id.gif_animation);
	    view.setGIFResource(R.drawable.skingif1);
	    
	    // Add the listener to the Button.
	    addContent();
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
	
	public void addContent() {
		buttonViewGraph = (Button) findViewById(R.id.btnViewGraph);
		buttonViewGraph.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent displayGraphIntent = new Intent(AnimatePictures.this, DisplayGraphActivity.class);
				startActivity(displayGraphIntent);
			}
		});
	}
}
