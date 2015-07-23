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

public class PersonCommonActivity extends Activity {

	final Handler handler = new Handler(); 
	
	public PersonCommonActivity() { 
	}
	
	public final void  handlerPost( Runnable runnable , long delayMilis ) { 
		handler.postDelayed(runnable, delayMilis );
	}

}
