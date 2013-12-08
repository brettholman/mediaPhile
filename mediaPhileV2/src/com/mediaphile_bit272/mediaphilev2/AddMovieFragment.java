package com.mediaphile_bit272.mediaphilev2;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddMovieFragment extends Fragment {
	
	Button titleSearchButton;
	Button saveMovieButton;
	
	OnClickListener titleSearchButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.d("SUCCESS!! ", "The search button has been clicked.");
		}
		 
	 };
	 
	 OnClickListener saveMovieButtonListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("SUCCESS!! ", "The save movie button has been clicked.");
			}
			 
		 };
	  
	 @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
		 
		  View view = inflater.inflate(R.layout.layout_addmoviefragment, null);				 
	      return view;
	   }
	 public void onActivityCreated(Bundle savedInstanceState) {
		 super.onActivityCreated(savedInstanceState);
		 
		 titleSearchButton = (Button) getView().findViewById(R.id.titleSearchButton);
		 titleSearchButton.setOnClickListener(titleSearchButtonListener);
		 
		 saveMovieButton = (Button) getView().findViewById(R.id.saveMovieButton);
		 saveMovieButton.setOnClickListener(saveMovieButtonListener);
		 
		 Log.d("ACTIVITY!! ", "The AddMovieFragment activity has been created.");
	 }
	 
}