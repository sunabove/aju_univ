package com.addressBook;

import java.io.*;
import java.text.*;
import java.util.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class SplashActivity extends PersonCommonActivity {

	ImageView logoIv;
	TextView appNameTv;
	ProgressBar progressBar;
	boolean activityStarted;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		this.logoIv = (ImageView) findViewById(R.id.splash_logo);
		this.appNameTv = (TextView) findViewById(R.id.splash_appName);
		this.progressBar = (ProgressBar) findViewById(R.id.splash_progress);

		this.logoIv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				activityStarted = true;
				Intent intent = new Intent(SplashActivity.this, PersonListActivity.class);
				startActivity(intent);
			}
		});

		this.appNameTv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				activityStarted = true;
				Intent intent = new Intent(SplashActivity.this, AboutActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();

		progressBar.setProgress(0);
		
		if (!activityStarted) {

			final Handler handler = new Handler();
			Runnable nextActivityRunnable = new Runnable() {
				ProgressBar progressBar = SplashActivity.this.progressBar;

				public void run() {
					if (activityStarted) {
						progressBar.setProgress(100);
					} else if (progressBar.getProgress() < 100) {
						progressBar.setProgress(progressBar.getProgress() + 10);
						handler.postDelayed(this, 400);
					} else if (!activityStarted) {
						activityStarted = true;
						Intent intent = new Intent(SplashActivity.this, PersonListActivity.class);
						startActivity(intent);
					}
				}
			};

			handler.postDelayed(nextActivityRunnable, 2000);
		}
	}

}
