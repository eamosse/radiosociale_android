package fr.unice.i3s.wimmics.radio;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;
import fr.unice.i3s.wimmics.radio.utilities.MenuContent;

public class MainActivity extends ListActivity {

	GridView gridView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		setListAdapter(new ArrayAdapter<MenuContent.MenuItem>(this,
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, MenuContent.ITEMS));
	}
 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
		switch(position){
		case 0:
			startActivity(new Intent(this, FrequencyListActivity.class)); 
		break; 
			
		case 1: 
			Toast.makeText(this, MenuContent.ITEMS.get(position).description + " --> Not implemented yet", Toast.LENGTH_LONG).show();
		break; 
			
		}

		
	}

}
