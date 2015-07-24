package com.addressBook;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.graphics.Bitmap;
import android.os.*; 
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.*;
import android.view.View.*;
import android.widget.*; 

public class PersonEditActivity extends PersonCommonActivity {
	
	Button saveBtn ;
	Button editBtn ; 
	Button cancelBtn ;  
	
	EditText personNameEt ;
	RadioButton personMaleRd ; 
	RadioButton personFemaleRd ; 
	EditText personBirthDateEt ; 
	EditText personPhoneNoEt ;
	EditText personAddrEt ;
	EditText personMemoEt ; 
	
	ImageView personPhotoIv ; 
	String imageFileName ; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_edit);
		
		this.saveBtn = (Button) findViewById( R.id.edit_save_btn );
		this.editBtn = (Button) findViewById( R.id.edit_edit_btn );
		this.cancelBtn = (Button) findViewById( R.id.edit_cancel_btn );
		
		this.personNameEt = (EditText) findViewById( R.id.edit_person_name );
		this.personBirthDateEt = (EditText) findViewById( R.id.edit_person_birth_date );
		this.personPhoneNoEt = (EditText) findViewById( R.id.edit_person_phone_number );
		this.personAddrEt = (EditText) findViewById( R.id.edit_person_address );
		
		this.personMaleRd = (RadioButton) findViewById( R.id.edit_person_gender_male );
		this.personFemaleRd = (RadioButton) findViewById( R.id.edit_person_gender_female );
		
		this.personPhotoIv = (ImageView) findViewById(R.id.edit_person_photo );
		this.personMemoEt = (EditText) findViewById( R.id.edit_person_memo );
		
		this.editBtn.setVisibility( View.GONE );
		
		this.saveBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				savePerson();
			} 
		});
		
		this.cancelBtn.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				cancelEdit();
			} 
		});
		
		this.personPhotoIv.setOnClickListener( new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				dispatchTakePictureIntent();
			} 
		});
	} 
	
	@Override
	public void onResume() {
		super.onResume();
		
		this.imageFileName = null ; 
	}
	
	public void savePerson() {
		String imageFileName = this.imageFileName ; 
		String name = this.personNameEt.getText().toString() ;
		
		if( name == null || name.trim().length() < 1 ) { 	
			
			Activity activity = this; 
			String title = "이름 입력" ;
			String message = "이름을 입력하세요!";
			
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			// Set the dialog title
			builder.setTitle( title ); 
			builder.setMessage( message );

			// Set the action buttons
			builder.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {  
				}
			});

			// set the negative button
			builder.setNegativeButton( "Cancel" , new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
				}
			});

			AlertDialog dialog = builder.create();
			dialog.show();
			
			return ; 
		}
		
		Person.Gender gender = this.personMaleRd.isChecked() ? Person.Gender.MALE : Person.Gender.FEMALE ;
		Date birthDate = null ;
		String birthDateStr = this.personBirthDateEt.getText().toString();
		try {
			SimpleDateFormat dateFormat =new SimpleDateFormat("yyyyMMDD");
			birthDate = dateFormat.parse( birthDateStr );
		} catch (Exception e) { 
		}
		String phoneNumber = this.personPhoneNoEt.getText().toString() ;
		String address =this.personAddrEt.getText().toString();
		String memo = this.personMemoEt.getText().toString();
		
		Person p = new Person();
		p.name = name.trim() ;
		p.gender = gender ; 
		p.birthDate = birthDate ;
		p.phoneNumber = phoneNumber ;
		p.address = address ; 
		p.memo = memo ; 
		p.imageFileName = imageFileName ; 
		
		AddressBook addressBook = AddressBook.getAddressBook();
		
		p.id = addressBook.persons.size() ; 
		addressBook.persons.add( p );
		
		addressBook.personInserted = true ; 
		
		addressBook.saveAllPersonsOnFile();
		
		finish(); 
	}
	
	public void cancelEdit() {
		finish();
	}
	
	public void viewList() { 
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
		ImageView imageView = this.personPhotoIv ; 
		
		if (data == null) {
			msg = "넘어온 데이터가 없습니다.";
		} else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			if (imageBitmap == null) {
				msg = "사진 데이터가 없습니다. 사진을 먼저 찍어주세요."; 
			} else if (imageBitmap != null) {
				File savedImageFile = null;
				try {
					savedImageFile = this.saveImageToFile( imageBitmap );
				} catch (IOException e) { 
					savedImageFile = null ; 
				}
				
				if( savedImageFile == null ) {
					// show error message
				} else if( savedImageFile != null ) {
					imageView.setImageBitmap(imageBitmap);
					try {
						this.imageFileName = savedImageFile.getCanonicalPath() ;
					} catch (IOException e) { 
					} 
				}
			}
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
