package fr.unice.i3s.wimmics.radio;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.reflect.TypeToken;

import fr.unice.i3s.wimmics.radio.model.Category;
import fr.unice.i3s.wimmics.radio.model.Frequency;
import fr.unice.i3s.wimmics.radio.utilities.AjaxRequest;
import fr.unice.i3s.wimmics.radio.utilities.ApplicationConstant;
import fr.unice.i3s.wimmics.radio.utilities.GridviewAdapter;
import fr.unice.i3s.wimmics.radio.utilities.JsonHelper;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class HomeActivity extends Activity implements AjaxRequest {
	 private GridviewAdapter mAdapter;
	    
	 private GridView gridView;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.grow_from_top, R.anim.grow_from_bottom);
		setContentView(R.layout.activity_home);
		gridView = (GridView) findViewById(R.id.gridView1);
        asyncJson();
       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public void asyncJson() {
		// TODO Auto-generated method stub
		String url = getString(R.string.base_service_url)+ "category";
        
		ProgressDialog dialog = new ProgressDialog(this);

		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setInverseBackgroundForced(false);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setTitle("Loading Category please wait...");
		
        final AQuery aq = new AQuery(this);
        
        AjaxCallback<String> ac = new AjaxCallback<String>() {

            @Override
            public void callback(String url, String json, AjaxStatus status) {
System.out.println(url + " " +json );
                    if(json != null){
                            //successful ajax call, show status code and json content
                           // Toast.makeText(aq.getContext(), status.getCode() + ":" + json.toString(), Toast.LENGTH_LONG).show();
                   	 Type listType = new TypeToken<ArrayList<Category>>() {
                        }.getType();
                        System.out.println("Categories : "+json);
                	ArrayList<Category> categories = (ArrayList<Category>) JsonHelper.getListObjectFromJson(json, listType);
                	mAdapter = new GridviewAdapter(HomeActivity.this,categories);
                	 // Set custom adapter to gridview
                   
                    gridView.setAdapter(mAdapter);
             
                    // Implement On Item click listener
                    gridView.setOnItemClickListener(new OnItemClickListener()
                    {
                     
            			@Override
            			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
            					long arg3) {
            				Category c = (Category)mAdapter.getItem(arg2); 
            				Toast.makeText(HomeActivity.this, c.getName(), Toast.LENGTH_SHORT).show();
            				Intent intent = new Intent(HomeActivity.this, FrequenciesActivity.class);
            				intent.putExtra(ApplicationConstant.CATEGORY_KEY, c.getId());
            				System.out.println(c.getId());
            				startActivity(intent);
            			}
                    });
                    }else{
                   	  
                       Toast.makeText(aq.getContext(), "Error:" + status.getMessage(), Toast.LENGTH_LONG).show();
                    }
            }
    };
    ac.header("Accept", "application/json");
   
    aq.progress(dialog).ajax(url, String.class, ac);
		
	}
	
}
