package com.example.skinimaging;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ConfirmTagActivity extends Activity {
	ImageView image;
	Button buttonYes;
	Button buttonNo;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CONFIRM_TAG_ACTIVITY_REQUEST_CODE = 200;
	public static final int MEDIA_TYPE_IMAGE = 1;
	private Uri fileUri;
	
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
		
		// If the region is properly tagged, then you should take the user to see the animated picture.
		buttonYes = (Button) findViewById(R.id.btnYes);
		buttonYes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent animatePictureIntent = new Intent(ConfirmTagActivity.this, AnimatePictures.class);
				startActivity(animatePictureIntent);
			}
		});
		
		// If the region is not properly tagged, then the user will be given the option to take another picture.
		buttonNo = (Button) findViewById(R.id.btnNo);
		buttonNo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				fileUri = Uri.fromFile(Options.getOutputMediaFile(MEDIA_TYPE_IMAGE)); // create a file to save the image
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

			    // start the image capture Intent
			    startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			}
		});
		
	}
}
