package com.example.skinimaging;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ConfirmTagActivity extends Activity {
	ImageView image;
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_tag);
		addContent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirm_tag, menu);
		return true;
	}

	public void addContent() {
		image = (ImageView) findViewById(R.id.photoConfirm);
		button = (Button) findViewById(R.id.btnYes);
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View view) {
				//Intent displayPictureIntent = new Intent(ConfirmTagActivity.this, DisplayPhotosActivity.class);
				//startActivity(displayPictureIntent);
			}
 
		});
		
	}
}
