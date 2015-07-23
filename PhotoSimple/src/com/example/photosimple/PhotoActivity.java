package com.example.photosimple;

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

public class PhotoActivity extends Activity {
	
	ImageView imageView ; 
	Button	takePhotoBtn ;
	TextView	messageTv ; 
	
	int takePhotoCount ;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		
		this.takePhotoBtn = (Button) findViewById( R.id.photo_takePhoto );
		this.messageTv = (TextView) findViewById( R.id.photo_message );
		this.imageView = (ImageView) findViewById( R.id.photo_imageView );
		
		this.takePhotoBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				dispatchTakePictureIntent();
			}
			
		});
	} 
	
	static final int REQUEST_IMAGE_CAPTURE = 1;

	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String msg = null ;
		if( data == null ) {
			msg = "넘어온 데이터가 없습니다."; 
		} else  if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        if( imageBitmap == null ) {
	        	msg = "사진 데이터가 없습니다." ; 
	        } else if( imageBitmap != null ) { 
	        	imageView.setImageBitmap(imageBitmap);
	        	takePhotoCount ++ ;
	        	msg = "%d번째 사진을 성공적으로 찍었습니다.";
	        	msg = String.format( msg, takePhotoCount );
	        }
	    }
		
		if( msg != null ) {
			messageTv.setText( msg );
		}
	}
}
