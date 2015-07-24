package com.addressBook;

import java.util.List;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import static com.addressBook.Person.Gender;
import static com.addressBook.Person.Gender.*;

public class PersonAdapter extends ArrayAdapter<Person> {

	LayoutInflater layoutInflater;
	AddressBook addressBook ; 

	public PersonAdapter(Context context, AddressBook addressBook ) {
		super(context, R.layout.person_item_view, addressBook.persons );

		this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.addressBook = addressBook; 
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View itemView;

		if (convertView == null) { 
			itemView = layoutInflater.inflate( R.layout.person_item_view, parent, false);
		} else {
			itemView = convertView;
		}

		ImageView personPhotoIv = (ImageView) itemView.findViewById(R.id.person_item_photo);
		RadioButton personChecked = (RadioButton) itemView.findViewById(R.id.person_item_checked);
		TextView personNameTv = (TextView) itemView.findViewById(R.id.person_item_name);  
		TextView personPhoneNoTv = (TextView) itemView.findViewById( R.id.person_item_phone_number );
		
		Person person = addressBook.persons.get( position );
		
		Gender gender = MALE ; 
		
		personChecked.setChecked( false );
		personNameTv.setText( person.name );
		personPhoneNoTv.setText( person.phoneNumber );

		return itemView;
	}

}
