package com.addressBook;

import java.io.File;
import java.util.List;








import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
	
	PersonListActivity personListActivity ; 

	public PersonAdapter( Context context, AddressBook addressBook, PersonListActivity personListActivity ) {
		super( context, R.layout.item_view_person, addressBook.persons ); 
		
		this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		this.addressBook = addressBook;  
		this.personListActivity = personListActivity ; 
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View itemView;

		if (convertView == null) { 
			itemView = layoutInflater.inflate( R.layout.item_view_person, parent, false);
		} else {
			itemView = convertView;
		} 
		 
		itemView.setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View v) { 
				personListActivity.onItemClickListPerson(position);
			} 
		}); 

		ImageView personPhotoIv = (ImageView) itemView.findViewById(R.id.person_item_photo);
		RadioButton personChecked = (RadioButton) itemView.findViewById(R.id.person_item_checked);
		TextView personNameTv = (TextView) itemView.findViewById(R.id.person_item_name);  
		TextView personPhoneNoTv = (TextView) itemView.findViewById( R.id.person_item_phone_number );
		
		Person person = addressBook.persons.get( position ); 
		
		personChecked.setChecked( false );
		personNameTv.setText( person.name );
		personPhoneNoTv.setText( person.phoneNumber );
		
		String imageFileName = person.imageFileName ;
		if( imageFileName != null && imageFileName.length() > 0 ) { 
			try { 
				personPhotoIv.setImageURI(Uri.fromFile( new File( imageFileName ) ));
			} catch ( Exception e ) {
				personPhotoIv.setImageResource( R.drawable.person_photo );
			} 
		}

		return itemView;
	}

}
