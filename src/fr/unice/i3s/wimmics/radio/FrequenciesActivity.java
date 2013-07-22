package fr.unice.i3s.wimmics.radio;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.reflect.TypeToken;

import fr.unice.i3s.wimmics.radio.model.Category;
import fr.unice.i3s.wimmics.radio.model.Frequency;
import fr.unice.i3s.wimmics.radio.model.Message;
import fr.unice.i3s.wimmics.radio.model.Response;
import fr.unice.i3s.wimmics.radio.utilities.ActionItem;
import fr.unice.i3s.wimmics.radio.utilities.AjaxRequest;
import fr.unice.i3s.wimmics.radio.utilities.ApplicationConstant;
import fr.unice.i3s.wimmics.radio.utilities.FrequencyAdapter;
import fr.unice.i3s.wimmics.radio.utilities.GridviewAdapter;
import fr.unice.i3s.wimmics.radio.utilities.JsonHelper;
import fr.unice.i3s.wimmics.radio.utilities.QuickAction;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FrequenciesActivity extends Activity implements AjaxRequest {
	 private GridView gridView;
	 private FrequencyAdapter mAdapter;
	 public static Frequency frequency; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		gridView = (GridView) findViewById(R.id.gridView1);
        asyncJson();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.frequencies, menu);
		return true;
	}

	@Override
	public void asyncJson() {
		ProgressDialog dialog = new ProgressDialog(this);

		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setInverseBackgroundForced(false);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setTitle("Loading Category please wait...");
		
		 long category = getIntent().getLongExtra(ApplicationConstant.CATEGORY_KEY,0); 
	        String url = getString(R.string.base_service_url)+ "frequency/bycategory/"+category;
	        System.out.println(url);
	         final AQuery aq = new AQuery(this);
	         
	         AjaxCallback<String> ac = new AjaxCallback<String>() {

	             @Override
	             public void callback(String url, String json, AjaxStatus status) {

	                     if(json != null){
	                             //successful ajax call, show status code and json content
	                            // Toast.makeText(aq.getContext(), status.getCode() + ":" + json.toString(), Toast.LENGTH_LONG).show();
	                    	 System.out.println(json);
	                    	 Type listType = new TypeToken<ArrayList<Frequency>>() {
	                         }.getType();
	                 	@SuppressWarnings("unchecked")
						ArrayList<Frequency> frequencies = (ArrayList<Frequency>) JsonHelper.getListObjectFromJson(json, listType);
	                 	mAdapter = new FrequencyAdapter(FrequenciesActivity.this,frequencies);
	                	 // Set custom adapter to gridview
	                   
	                    gridView.setAdapter(mAdapter);
	             
	                    // Implement On Item click listener
	                    gridView.setOnItemClickListener(new OnItemClickListener()
	                    {
	                     
	            			@Override
	            			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	            					long arg3) {
	            				//itemClick(arg0,arg1, arg2, arg3);
	            				frequency= (Frequency)mAdapter.getItem(arg2); 
	            				Toast.makeText(FrequenciesActivity.this, frequency.getName(), Toast.LENGTH_SHORT).show();
	            				Intent intent = new Intent(FrequenciesActivity.this, FrequencyActivity.class);
	            				//intent.putExtra(ApplicationConstant.CATEGORY_KEY, c.getId());
	            				//System.out.println(c.getId());
	            				startActivity(intent);
	            			}
	                    });
	                     
	                     }else{
	                    	    
	                             //ajax error, show error code
	                        Toast.makeText(aq.getContext(), "Error:" + status.getCode(), Toast.LENGTH_LONG).show();
	                     }
	             }
	     };
	     ac.header("Accept", "application/json");
	   
	     aq.progress(dialog).ajax(url, String.class, ac);
			
	}
	
	private void itemClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3){
		frequency= (Frequency)mAdapter.getItem(arg2); 
		final QuickAction quickAction = new QuickAction(this,
				QuickAction.HORIZONTAL);
		if(FrequenciesActivity.frequency!=null){
			for(Response s: FrequenciesActivity.frequency.getTags()){
				ActionItem ac = new ActionItem(); 
				ac.setTitle(s.getText()); 
				//ac.setIcon(getResources().getDrawable(R.drawable.ic_btn_speak_now));
				ac.setUrl_icon(getString(R.string.base_url) + s.getImage());
				ac.setActionId(s.getId()); 
				quickAction.addActionItem(ac);
			}
		}
		quickAction.perform();

		//Set listener for action item clicked
				quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {			
				

					@Override
					public void onItemClick(QuickAction source, int pos, long actionId) {				
						ActionItem actionItem = quickAction.getActionItem(pos);
						//Toast.makeText(getApplicationContext(), "You  report " + actionItem.getTitle(), Toast.LENGTH_SHORT).show();
						Response r = frequency.getTags().get(pos); 
						Toast.makeText(getApplicationContext(), "You  report " + r.getText(), Toast.LENGTH_SHORT).show();
						Message m = new Message(); 
						m.setContent(r); 
						m.setFrequency(frequency); 

					}
				});
				
				//set listnener for on dismiss event, this listener will be called only if QuickAction dialog was dismissed
				//by clicking the area outside the dialog.
				quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {			
					@Override
					public void onDismiss() {
						Toast.makeText(getApplicationContext(), "Dismissed", Toast.LENGTH_SHORT).show();
					}
				});
				
				quickAction.show(arg1);	
	}

}
