package com.addressBook;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.graphics.Bitmap;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class ViewPhotoActivity extends PersonCommonActivity {

	Intent intentFromPhotoApp;

	TextView messageTv;
	Button okBtn;
	Button taktPicBtn;
	ImageView photoIv;

	int takePhotoCnt;
	boolean viewOnly ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_photo);

		this.takePhotoCnt = 0;

		this.messageTv = (TextView) findViewById(R.id.view_photo_message);
		this.photoIv = (ImageView) findViewById(R.id.view_photo_take_photo);
		this.okBtn = (Button) findViewById(R.id.view_photo_ok);
		this.taktPicBtn = (Button) findViewById(R.id.view_photo_take);

		this.okBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickOkBtn();
			}
		});

		this.taktPicBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if( ! viewOnly ) { 
					onClickTakePhotoBtn();
				}
			}
		});

		this.photoIv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if( ! viewOnly ) { 
					onClickTakePhotoBtn();
				}
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();

		this.messageTv.setText("");
		
		Intent intent = this.getIntent();
		
		boolean viewOnly = intent.getBooleanExtra( "viewOnly", false );
		this.viewOnly = viewOnly ; 
		
		if( viewOnly ) {
			this.taktPicBtn.setVisibility( View.GONE );
		} else if (this.takePhotoCnt < 1) { 
			if (intent != null) {
				int reqeustCode = intent.getIntExtra("requestCode", -1);
				if (reqeustCode == super.REQUEST_IMAGE_CAPTURE) {
					this.onClickTakePhotoBtn();
				}
			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String msg = null;
		ImageView imageView = this.photoIv;

		if (data == null) {
			this.intentFromPhotoApp = null;
			msg = "넘어온 데이터가 없습니다.";
		} else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			imageView.setImageBitmap(imageBitmap);

			this.intentFromPhotoApp = data;
		}
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();

		String msg = null;
		msg = "Back button pressed.";

		this.messageTv.setText(msg);
	}

	public void onClickTakePhotoBtn() {
		this.takePhotoCnt ++ ; 
		this.dispatchTakePictureIntent();
	}

	public void onClickOkBtn() {
		Intent data = this.intentFromPhotoApp;
		int resultCode = Activity.RESULT_OK;
		this.setResult(resultCode, data);
		this.finish();
	}

}
