package com.example.skinimaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class DisplayPhotosActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("reached here");
		setContentView(R.layout.activity_display_photos);
		
		GridView gridview = (GridView) findViewById(R.id.gridview);

		gridview.setAdapter(new ImageAdapter(this));
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				//Toast.makeText(DisplayPhotosActivity.this, "" + position+ Long.toString(id), Toast.LENGTH_SHORT).show();
				startPictureIntent();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_photos, menu);
		return true;
	}
	
	public void startPictureIntent() {
		Intent animatePicturesIntent = new Intent(this, AnimatePictures.class);
		startActivity(animatePicturesIntent);
	}

}
