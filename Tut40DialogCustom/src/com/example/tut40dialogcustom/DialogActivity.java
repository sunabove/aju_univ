package com.example.tut40dialogcustom;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class DialogActivity extends Activity {

	TextView messageTv;

	Button normalDialogBtn;
	Button dontShowAnyMoreBtn;
	Button customDialogBtn;

	boolean dontShowAnyMore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);

		this.dontShowAnyMore = false;

		this.messageTv = (TextView) findViewById(R.id.dialog_message);

		this.normalDialogBtn = (Button) findViewById(R.id.dialog_show_normal_btn);
		this.dontShowAnyMoreBtn = (Button) findViewById(R.id.dialog_show_no_more_btn);
		this.customDialogBtn = (Button) findViewById(R.id.dialog_show_custom_btn);

		this.normalDialogBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showNormalDialog();
			}
		});

		this.dontShowAnyMoreBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (dontShowAnyMore) {
				} else if (!dontShowAnyMore) {
					showDontShowAnyMoreDialog();
				}
			}
		});

		this.customDialogBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showCustomDialog();
			}
		});
	}

	public void showMessage(String msg) {
		if (msg != null) {
			this.messageTv.setText(msg);
		}
	}

	public void showNormalDialog() {
		Activity activity = this;
		String title = "이름 입력";
		String message = "이름을 입력하세요!";

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		// Set the dialog title
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setIcon(R.drawable.message_icon);

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
	}

	public void showDontShowAnyMoreDialog() {

		final String[] items = { "Don't show any more!" };

		Activity activity = this;
		String title = "이름 입력 2";
		String message = "이름을 입력하세요!";

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		// Set the dialog title
		builder.setTitle(title);
		// builder.setMessage(message);
		builder.setIcon(R.drawable.message_icon);

		int currSelectedIndex = -1;

		builder.setSingleChoiceItems(items, currSelectedIndex, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					dontShowAnyMore = true;
				}
			}
		});

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
	}

	public void showCustomDialog() {

		Activity activity = this;
		String title = "이름 입력";
		
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
		editText.setText("이름을 입력하세요?");
		Button button = (Button) dialogView.findViewById(R.id.my_custom_btn);
		button.setText("Ok");

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if( dialogs[0] != null ) { 
					dialogs[0].hide();
				}
			}
		});

		AlertDialog dialog = builder.create();
		dialogs[0] = dialog;
		dialog.show();

	}
	
	public void showCustomDialog_02() {

		Activity activity = this;
		String title = "이름 입력"; 

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		// Set the dialog title
		builder.setTitle(title);
		builder.setIcon(R.drawable.message_icon);

		// Dialog View Customizing
		LayoutInflater inflater = activity.getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_my_custom, null);
		builder.setView(dialogView);
		
		final AlertDialog dialog = builder.create();

		EditText editText = (EditText) dialogView.findViewById(R.id.my_custom_edit);
		editText.setText("이름을 입력하세요?");
		Button button = (Button) dialogView.findViewById(R.id.my_custom_btn);
		button.setText("Ok");
		
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.hide();
			}
		}); 
		
		dialog.show();

	}
}
