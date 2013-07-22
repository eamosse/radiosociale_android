package fr.unice.i3s.wimmics.radio;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class LaunchActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.grow_from_top, R.anim.grow_from_bottom);
		setContentView(R.layout.first_run_choose_path);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launch, menu);
		return true;
	}
	
	public void onGetStartedClicked(View view){
		startActivity(new Intent(this, HomeActivity.class));
	}
	
	public void onSignInClicked(View view){
		Toast.makeText(this, "Not yet implemented", Toast.LENGTH_LONG).show();
	}

}
