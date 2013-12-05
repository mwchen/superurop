package com.example.skinimaging;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;

public class TakePhotosActivity extends Activity {
	Camera mCamera;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		String value = intent.getStringExtra("key");
		//takePhotos();
		setContentView(R.layout.activity_take_photos);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.take_photos, menu);
		return true;
	}
	
	private void takePhotos(int actionCode) {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    startActivityForResult(takePictureIntent, actionCode);
	}
	
	public static boolean isIntentAvailable(Context context, String action) {
	    final PackageManager packageManager = context.getPackageManager();
	    final Intent intent = new Intent(action);
	    List<ResolveInfo> list =
	            packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
	    return list.size() > 0;
	}
	
/*	private boolean safeCameraOpen(int id) {
	    boolean qOpened = false;
	  
	    try {
	        releaseCameraAndPreview();
	        mCamera = Camera.open(id);
	        qOpened = (mCamera != null);
	    } catch (Exception e) {
	        Log.e(getString(R.string.app_name), "failed to open Camera");
	        e.printStackTrace();
	    }

	    return qOpened;    
	}

	private void releaseCameraAndPreview() {
	    mPreview.setCamera(null);
	    if (mCamera != null) {
	        mCamera.release();
	        mCamera = null;
	    }
	}

	public void setCamera(Camera camera) {
	    if (mCamera == camera) { return; }
	    
	    stopPreviewAndFreeCamera();
	    
	    mCamera = camera;
	    
	    if (mCamera != null) {
	        List<Size> localSizes = mCamera.getParameters().getSupportedPreviewSizes();
	        mSupportedPreviewSizes = localSizes;
	        requestLayout();
	      
	        try {
	            mCamera.setPreviewDisplay(mHolder);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	      
	        
	          Important: Call startPreview() to start updating the preview surface. Preview must 
	          be started before you can take a picture.
	          
	        mCamera.startPreview();
	    }
	}*/
}
