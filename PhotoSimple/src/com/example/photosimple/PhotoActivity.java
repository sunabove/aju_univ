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

	ImageView imageView;
	Button takePhotoBtn;
	Button savePhotoBtn;
	TextView messageTv;

	int takePhotoCount;
	Bitmap imageBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);

		this.takePhotoBtn = (Button) findViewById(R.id.photo_takePhoto);
		this.savePhotoBtn = (Button) findViewById( R.id.photo_savePhoto );
		this.messageTv = (TextView) findViewById(R.id.photo_message);
		this.imageView = (ImageView) findViewById(R.id.photo_imageView);

		this.takePhotoBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dispatchTakePictureIntent();
			} 
		});
		
		this.savePhotoBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickSavePhotoBtn();
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
		String msg = null;
		if (data == null) {
			msg = "넘어온 데이터가 없습니다.";
		} else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			if (imageBitmap == null) {
				msg = "사진 데이터가 없습니다. 사진을 먼저 찍어주세요.";
				if( this.takePhotoCount > 1 ) {
					msg = "이미 저장된 사진입니다. 다른 사진을 먼저 \\찍어주세요.";
				}
			} else if (imageBitmap != null) {
				imageView.setImageBitmap(imageBitmap);
				this.imageBitmap = imageBitmap ; 
				takePhotoCount++;
				
				this.imageBitmap = null ; 
				msg = "%d번째 사진을 성공적으로 찍었습니다.";
				msg = String.format(msg, takePhotoCount);
			}
		}

		if (msg != null) {
			messageTv.setText(msg);
		}
	}
	
	public void onClickSavePhotoBtn() {
		Bitmap imageBitmap = this.imageBitmap;
		String msg = null;
		if( imageBitmap == null ) {
			msg = "저장할 사진이 없습니다.";
		} else if( imageBitmap != null ) {
			File imageFile = null ; 
			try {
				imageFile = this.saveImageToFile( imageBitmap );
			} catch (Exception e) {  
				e.printStackTrace();
			}
			if( imageFile == null ) {
				msg = "사진 파일을 저장하지 못하였습니다.";
			} else if( imageFile != null ) {
				msg = "사진 파일[%s]이 저장되었습니다.";
				String imageFileName = "";
				try {
					imageFileName = imageFile.getCanonicalPath();
				} catch (IOException e) { 
				}
				msg = String.format( msg, imageFileName );
			}
		}
		
		if( msg != null ) {
			messageTv.setText( msg );
		}
	}

	private File saveImageToFile(Bitmap imageBitmap) throws IOException {
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
