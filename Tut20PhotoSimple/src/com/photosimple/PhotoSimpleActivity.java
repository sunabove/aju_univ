package com.photosimple;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;
import android.provider.MediaStore;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class PhotoSimpleActivity extends Activity {

	Button takePhotoBtn;
	Button savePhotoBtn;
	TextView messageTv;
	ImageView photoIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_simple);

		this.takePhotoBtn = (Button) findViewById(R.id.photo_takePhoto);
		this.savePhotoBtn = (Button) findViewById(R.id.photo_savePhoto);

		this.messageTv = (TextView) findViewById(R.id.photo_message);

		this.photoIv = (ImageView) findViewById(R.id.photo_photoImage);

		this.takePhotoBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickTakePhotoBtn();
			}
		});

		this.savePhotoBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickSavePhotoBtn();
			}
		});
	}

	public void onClickTakePhotoBtn() {
		this.dispatchTakePictureIntent();
	}

	public void onClickSavePhotoBtn() {
	}

	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int REQUEST_TAKE_PHOTO = 1;

	private void dispatchTakePictureIntent() {
		String msg = null;
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// Ensure that there's a camera activity to handle the intent
		if (takePictureIntent.resolveActivity(getPackageManager()) == null) {
			msg = "사진 저장 기능을 지원하지 않습니다.";
		} else if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try {
				photoFile = createImageFile();
			} catch (IOException ex) {
				msg = "이미지 파일을 생성할 수 없습니다.";
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
			}
		}

		if (msg != null) {
			showMessage(msg);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		ImageView photoIv = this.photoIv;

		String msg = null;
		if (data == null) {
			msg = "사진  데이터가 없습니다.";
		} else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			photoIv.setImageBitmap(imageBitmap);
		}

		if (msg != null) {
			showMessage(msg);
		}
	}

	String mCurrentPhotoPath;

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "SUNABOVE_" + timeStamp + "_";
		File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File imgFile = File.createTempFile(imageFileName, /* prefix */
				".jpg", /* suffix */
				storageDir /* directory */
		);

		// Save a file: path for use with ACTION_VIEW intents
		mCurrentPhotoPath = "file:" + imgFile.getAbsolutePath();
		return imgFile;
	}

	public void showMessage(String msg) {
		this.messageTv.setText(msg);
	}
}
