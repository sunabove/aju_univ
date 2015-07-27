package com.addressBook;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.graphics.Bitmap;
import android.os.*; 
import android.provider.MediaStore;
import android.view.*;
import android.view.View.*;
import android.widget.*; 

public class PersonCommonActivity extends Activity {

	final Handler handler = new Handler(); 
	
	public PersonCommonActivity() { 
	}
	
	public final void  handlerPost( Runnable runnable , long delayMilis ) { 
		handler.postDelayed(runnable, delayMilis );
	}
	
	static final public int REQUEST_IMAGE_CAPTURE = 1; 
	
	public void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	} 
	
	public File saveImageToFile(Bitmap imageBitmap) throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "AJU_UNIV_" + timeStamp + ".png";
		File storageDir = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES) ;
		File imageFile = new File(storageDir, imageFileName);
		FileOutputStream out = new FileOutputStream( imageFile );
		
		imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, out ); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
		out.flush();
		out.close(); // do not forget to close the stream

		return imageFile;
	}
	

}
