package com.addressBook;

import java.io.*;
import java.text.*;
import java.util.*; 

import android.app.*;
import android.content.*;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class PersonEditActivity extends PersonCommonActivity {

	TextView messageTv;
	Button saveBtn;
	Button editBtn;
	Button cancelBtn;

	EditText personNameEt;
	RadioButton personMaleRd;
	RadioButton personFemaleRd;
	EditText personBirthDateEt;
	EditText personPhoneNoEt;
	EditText personAddrEt;
	EditText personMemoEt;

	ImageView personPhotoIv;
	String imageFileName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_edit);

		this.messageTv = (TextView) findViewById(R.id.edit_message);

		this.saveBtn = (Button) findViewById(R.id.edit_save_btn);
		this.editBtn = (Button) findViewById(R.id.edit_edit_btn);
		this.cancelBtn = (Button) findViewById(R.id.edit_cancel_btn);

		this.personNameEt = (EditText) findViewById(R.id.edit_person_name);
		this.personBirthDateEt = (EditText) findViewById(R.id.edit_person_birth_date);
		this.personPhoneNoEt = (EditText) findViewById(R.id.edit_person_phone_number);
		this.personAddrEt = (EditText) findViewById(R.id.edit_person_address);

		this.personMaleRd = (RadioButton) findViewById(R.id.edit_person_gender_male);
		this.personFemaleRd = (RadioButton) findViewById(R.id.edit_person_gender_female);

		this.personPhotoIv = (ImageView) findViewById(R.id.edit_person_photo);
		this.personMemoEt = (EditText) findViewById(R.id.edit_person_memo);

		this.messageTv.setText("");

		if (this instanceof PersonViewActivity) {
			// if this activity is View activity
		} else {
			// if this activity is Edit activity
			this.editBtn.setVisibility(View.GONE);

			this.saveBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					savePerson();
				}
			});

			this.cancelBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					cancelEdit();
				}
			});

			this.personPhotoIv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					takePhoto();
				}
			});
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		AddressBook addressBook = AddressBook.getAddressBook();
		Person person = addressBook.personSelected;
		if (person != null) {
			this.showPersonOnUi(person);
		}
	}

	public void showPersonOnUi(Person person) {
		if (person == null) {
			return;
		}

		// name
		this.personNameEt.setText(person.name);
		// gender
		this.personMaleRd.setChecked(person.gender == Person.Gender.MALE);
		// birth date
		if (person.birthDate != null) {
			this.personBirthDateEt.setText(AddressBook.BIRTH_DATE_FORMAT.format(person.birthDate));
		}
		
		// phone number
		this.personPhoneNoEt.setText( person.phoneNumber );
		// address
		this.personAddrEt.setText( person.address );
		
		// photo
		String imageFileName = person.imageFileName ;
		if( imageFileName != null && imageFileName.length() > 0 ) { 
			try { 
				personPhotoIv.setImageURI(Uri.fromFile( new File( imageFileName ) ));
			} catch ( Exception e ) {
				personPhotoIv.setImageResource( R.drawable.person_photo );
			} 
		}
		// memo
		this.personMemoEt.setText( person.memo );
	}

	public void savePerson() {
		String imageFileName = this.imageFileName;
		String name = this.personNameEt.getText().toString();

		if (name == null || name.trim().length() < 1) {

			Activity activity = this;
			String title = "이름 입력";
			String message = "이름을 입력하세요!";

			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			// Set the dialog title
			builder.setTitle(title);
			builder.setMessage(message);

			// Set the action buttons
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
				}
			});

			// set the negative button
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
				}
			});

			AlertDialog dialog = builder.create();
			dialog.show();

			return;
		}

		Person.Gender gender = this.personMaleRd.isChecked() ? Person.Gender.MALE : Person.Gender.FEMALE;

		Date birthDate = null;
		String birthDateStr = this.personBirthDateEt.getText().toString();
		SimpleDateFormat dateFormat = AddressBook.BIRTH_DATE_FORMAT;
		try {
			birthDate = dateFormat.parse(birthDateStr);
		} catch (Exception e) {
			try {
				dateFormat = new SimpleDateFormat("yyyyMMDD");
				birthDate = dateFormat.parse(birthDateStr);
			} catch (Exception e1) {
			}
		}

		String phoneNumber = this.personPhoneNoEt.getText().toString();
		String address = this.personAddrEt.getText().toString();
		String memo = this.personMemoEt.getText().toString();

		Person p = new Person();
		p.name = name.trim();
		p.gender = gender;
		p.birthDate = birthDate;
		p.phoneNumber = phoneNumber;
		p.address = address;
		p.memo = memo;
		p.imageFileName = imageFileName;

		AddressBook addressBook = AddressBook.getAddressBook();

		p.id = addressBook.persons.size();
		addressBook.persons.add(p);

		addressBook.personInserted = true ;

		addressBook.saveAllPersonsOnFile();
		
		// show message dialog
		
		final Activity activity = this;
		String title = "저장 완료";
		String msg = "주소록이 저장되었습니다.";
		
		final AlertDialog [] dialogs = { null };

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		// Set the dialog title
		builder.setTitle(title);
		builder.setIcon(R.drawable.message_icon);

		// Dialog View Customizing
		LayoutInflater inflater = activity.getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_my_custom, null);
		builder.setView(dialogView);

		EditText editText = (EditText) dialogView.findViewById(R.id.my_custom_edit);
		editText.setText( msg );
		Button button = (Button) dialogView.findViewById(R.id.my_custom_btn);
		button.setText("확인");

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if( dialogs[0] != null ) { 
					dialogs[0].hide();
					activity.finish();
				}
			}
		});

		AlertDialog dialog = builder.create();
		dialogs[0] = dialog;
		dialog.show(); 
	}

	public void cancelEdit() {
		finish();
	}

	public void viewList() {
	}

	public void takePhoto() {
		int requestCode = REQUEST_IMAGE_CAPTURE;

		Intent intent = new Intent(this, ViewPhotoActivity.class);
		intent.putExtra("requestCode", requestCode);

		this.startActivityForResult(intent, requestCode);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String msg = null;
		ImageView imageView = this.personPhotoIv;

		if (data == null) {
			msg = "넘어온 데이터가 없습니다.";
		} else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			if (imageBitmap == null) {
				msg = "사진 데이터가 없습니다. 사진을 먼저 찍어주세요.";
			} else if (imageBitmap != null) {
				File savedImageFile = null;
				try {
					savedImageFile = this.saveImageToFile(imageBitmap);
				} catch (IOException e) {
					savedImageFile = null;
				}

				if (savedImageFile == null) {
					// show error message
				} else if (savedImageFile != null) {
					imageView.setImageBitmap(imageBitmap);
					try {
						this.imageFileName = savedImageFile.getCanonicalPath();
					} catch (IOException e) {
					}
				}
			}
		}

		if (msg != null) {
			this.messageTv.setText(msg);
		}
	}

}
