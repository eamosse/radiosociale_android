package fr.unice.i3s.wimmics.radio;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.reflect.TypeToken;

import fr.unice.i3s.wimmics.radio.model.Feature;
import fr.unice.i3s.wimmics.radio.model.Frequency;
import fr.unice.i3s.wimmics.radio.model.Instant;
import fr.unice.i3s.wimmics.radio.model.Message;
import fr.unice.i3s.wimmics.radio.model.Place;
import fr.unice.i3s.wimmics.radio.model.Response;
import fr.unice.i3s.wimmics.radio.utilities.ActionItem;
import fr.unice.i3s.wimmics.radio.utilities.JsonHelper;
import fr.unice.i3s.wimmics.radio.utilities.QuickAction;
import fr.unice.i3s.wimmics.radio.utils.GooglePlace;

public class FrequencyActivity extends FragmentActivity implements
		 OnInfoWindowClickListener, OnMarkerDragListener, OnMapClickListener, OnCameraChangeListener {
	
	   private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 50; // in Meters
	   private static final long MINIMUM_TIME_BETWEEN_UPDATES = 120000; // in Milliseconds

	private GoogleMap mMap;

	private Marker mPerth;
	private TextView mTopText;
	private Frequency frequency;
	Context context; 
	Feature feature;
	boolean canBeMove=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		mTopText = (TextView) findViewById(R.id.top_text);;
		mTopText.setText(FrequenciesActivity.frequency.getDescription());
		context = this; 
		setUpMapIfNeeded();
		frequency = FrequenciesActivity.frequency; 
	}
	
	public void onPause(){
		super.onPause();
	}



	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}
	
    @Override
    public void onMapClick(LatLng point) {
    	mMap.clear();
        mTopText.setText("tapped, point=" + point);
        mMap.addMarker(new MarkerOptions().position(point).title("Marker"));
        changeCamera(true);
       
        getPosition(point.latitude, point.longitude);
        canBeMove=false;
    }

	private void setUpMap() {
		// Hide the zoom controls as the button panel will cover it.
		mMap.getUiSettings().setZoomControlsEnabled(false);

		mMap.setOnInfoWindowClickListener(this);
		mMap.setOnMarkerDragListener(this);
		mMap.setOnMapClickListener(this);
		mMap.setTrafficEnabled(true);
		mMap.setOnCameraChangeListener(this);

		// Pan to see all markers in view.
		// Cannot zoom to bounds until the map has a size.
		final View mapView = getSupportFragmentManager().findFragmentById(
				R.id.map).getView();
		if (mapView.getViewTreeObserver().isAlive()) {
			mapView.getViewTreeObserver().addOnGlobalLayoutListener(
					new OnGlobalLayoutListener() {
						@SuppressWarnings("deprecation")
						// We use the new method when supported
						@SuppressLint("NewApi")
						// We check which build version we are using.
						@Override
						public void onGlobalLayout() {
//							LatLngBounds bounds = new LatLngBounds.Builder()
//									.include(PERTH).build();
//							if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
//								mapView.getViewTreeObserver()
//										.removeGlobalOnLayoutListener(this);
//							} else {
//								mapView.getViewTreeObserver()
//										.removeOnGlobalLayoutListener(this);
//							}
//							mMap.moveCamera(CameraUpdateFactory
//									.newLatLngBounds(bounds, 50));
						}
					});
		}
	}

	

	private boolean checkReady() {
		if (mMap == null) {
			Toast.makeText(this, R.string.map_not_ready, Toast.LENGTH_SHORT)
					.show();
			return false;
		}
		return true;
	}

	/** Called when the Clear button is clicked. */
	public void onClearMap(View view) {
		if (!checkReady()) {
			return;
		}
		mMap.clear();
	}

	/** Called when the Reset button is clicked. */
	public void onResetMap(View view) {
		if (!checkReady()) {
			return;
		}
		// Clear the map because we don't want duplicates of the markers.
		mMap.clear();
		//addMarkersToMap();
	}

	

	@Override
	public void onInfoWindowClick(Marker marker) {
		Toast.makeText(getBaseContext(), "Click Info Window",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onMarkerDragStart(Marker marker) {
		mTopText.setText("onMarkerDragStart");
	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
		mTopText.setText("onMarkerDragEnd");
	}

	@Override
	public void onMarkerDrag(Marker marker) {
		mTopText.setText("onMarkerDrag.  Current Position: "
				+ marker.getPosition());
	}
	
	Message m ;

	public void test(View view) {
		// Add action item
		
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
						m = new Message(); 
						m.setContent(r); 
						m.setFrequency(frequency); 
						sendMessage(m);
						//getPosition(); 
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
				
				quickAction.show(view);	
				}
	Point p;
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	 
	   int[] location = new int[2];
	   ImageView button = (ImageView) findViewById(R.id.imageView1);
	 
	   // Get the x, y location and store it in the location[] array
	   // location[0] = x, location[1] = y.
	   button.getLocationOnScreen(location);
	 
	   //Initialize the Point with x, and y positions
	   p = new Point();
	   p.x = location[0];
	   p.y = location[1];
	}
	double longitude; 
	double latitude;
	private MyLocationListener locationListener;
	private LocationManager locationManager; 
	
	private void getPosition(double latitude, double longitude){
		final AQuery aq = new AQuery(this); 
		String url = "http://maps.googleapis.com/maps/api/geocode/json";
		url += "?latlng="+latitude + "," + longitude + "&sensor=true";
		AjaxCallback<String> ac = new AjaxCallback<String>() {
            @Override
            public void callback(String url, String json, AjaxStatus status) {
            	System.out.println(json);
                    if(json != null){
                    	String code = JsonHelper.JsonKeyValue(json, "status");
                    	//GooglePlace selected = null; 
                    	if(code.equalsIgnoreCase("OK"))
                    	{
                    		Feature newFeature = null;
                             Type listType = new TypeToken<ArrayList<GooglePlace>>() {
                             }.getType();
                     	    ArrayList<GooglePlace> places = (ArrayList<GooglePlace>) JsonHelper.getListObjectFromJson(json, listType, "results");
                     	    for(GooglePlace google: places){
                     	    	for(Feature f : google.getAddress_components()){
                     	    		for(String s : f.getTypes()) {
                     	    			   if(s.equalsIgnoreCase("route")){
                     	    				  newFeature = f;
                     	    				  f.setPoint(google.getGeometry().getLocation());
                     	    				break; 
                     	    			}
                     	    		}//end of for types
                     	    			
                     	    		if(newFeature!=null){
                     	    			break;
                     	    		}
                     	    		 
                     	    	}//end of for features
                     	    	if(newFeature!=null){
                 	    			feature =newFeature; 
                 	    			mTopText.setText("Vous etes sur la route : " + feature.getName());
                 	    			break;
                 	    		}
                     	    }//end of for place
                     	   
                             }else{
                                     //ajax error, show error code
                                Toast.makeText(aq.getContext(), "Error:" + status.getCode(), Toast.LENGTH_LONG).show();
                             }
                    	}
            }
    };
    ac.header("Accept", "application/json");
    aq.ajax(url, String.class, ac);
	}
	
	public void sendMessage(Message m){
		    m.setFeature(feature);
		   
		    Instant i = new Instant(); 
	    	i.setDate(new Date(System.currentTimeMillis()));
	    	m.setPublishDate(i);
	    	//sendMessage(m);
		AQuery aq = new AQuery(context);
		String url = getString(R.string.base_service_url ) + "message";
		System.out.println("this is the url " + url);
		AjaxCallback<String> ac = new AjaxCallback<String>() {
            @Override
            public void callback(String url, String json, AjaxStatus status) {
            	
            	System.out.println("Response " + json + " Code " + status.getMessage() + " Message " + status.getCode());
            	Toast.makeText(context, "Response " + json + " Code " + status.getMessage() + " Message " + status.getCode(), Toast.LENGTH_LONG).show();
            }
		};
//		Map<String, Object> params = new HashMap<String, Object>();
//	    params.put("message", JsonHelper.createJsonObject(m));
	    //List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        //pairs.add(new BasicNameValuePair("message", JsonHelper.createJsonObject(m).toString())); 
        StringEntity m_entity;
		try {
			m_entity = new StringEntity(JsonHelper.createJsonObject(m).toString(), "UTF-8");
			Log.v("request",JsonHelper.createJsonObject(m).toString());
			   //entity = new UrlEncodedFormEntity(pairs, "UTF-8");
			 Map<String, Object> params = new HashMap<String, Object>();
			 params.put(AQuery.POST_ENTITY, m_entity);
			 ac.header("Accept", "*/*"); 
			 ac.header("Content-Type", "application/json");
			 aq.ajax(url, params, String.class,ac);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
	}
    @Override
    protected void onStart() {
        super.onStart();

	
	 LocationManager locationManager =
             (LocationManager) getSystemService(Context.LOCATION_SERVICE);
     final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

     if (!gpsEnabled) {
         new AlertDialog.Builder(context)
                 .setTitle(R.string.app_name)
                 .setMessage(R.string.no_gps)
                 .setPositiveButton(R.string.active_gps, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         enableLocationSettings();
                     }
                 })
                 .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         finish();
                     }
                 })
                 .setOnCancelListener(new DialogInterface.OnCancelListener() {
                     @Override
                     public void onCancel(DialogInterface dialogInterface) {
                         finish();
                     }
                 })
                 .show();
     }
 }

 private void enableLocationSettings() {
     Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
     startActivity(settingsIntent);
 }

 @Override
 public void onResume() {
     super.onResume();
     setUpMapIfNeeded();
     if (locationListener == null)
         locationListener = new MyLocationListener();
     locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
     //String locationProvider = LocationManager.GPS_PROVIDER;
     Criteria criteria = new Criteria();
     criteria.setAccuracy(Criteria.ACCURACY_COARSE);
     criteria.setCostAllowed(false);
     String providerName = locationManager.getBestProvider(criteria, true);
     if (providerName != null) {
         Location lastKnownLocation = locationManager.getLastKnownLocation(providerName);
         setLocation(lastKnownLocation); 
         locationManager.requestLocationUpdates(
                 providerName,
                 MINIMUM_TIME_BETWEEN_UPDATES,
                 MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                 new MyLocationListener()
         );
     }
 }
 
 private void changeCamera(boolean move) {
	 if(move){
	 CameraPosition mPosition =
             new CameraPosition.Builder().target(new LatLng(latitude, longitude))
                     .zoom(15.5f)
                     .bearing(0)
                     .tilt(25)
                     .build();
     mMap.animateCamera(CameraUpdateFactory.newCameraPosition(mPosition));
	 }
     
 }
 
 private void setLocation(Location location){
	 if(location!=null){
		 longitude = location.getLongitude(); 
         latitude = location.getLatitude(); 
         changeCamera(canBeMove);
         if(canBeMove)
         getPosition(latitude, longitude);
	 }
 }
 
 protected class MyLocationListener implements LocationListener {

     public void onLocationChanged(Location location) {
//         Toast.makeText(context, "Provider status changed : onLocationChanged ",
//                 Toast.LENGTH_LONG).show();
        setLocation(location); 
     }

     public void onStatusChanged(String s, int i, Bundle b) {
//         Toast.makeText(context, "Provider status changed " + s,
//                 Toast.LENGTH_LONG).show();
     }

     public void onProviderDisabled(String s) {
//         Toast.makeText(context,
//                 "Provider disabled by the user. GPS turned off",
//                 Toast.LENGTH_LONG).show();
     }

     public void onProviderEnabled(String s) {
//         Toast.makeText(context,
//                 "Provider enabled by the user. GPS turned on",
//                 Toast.LENGTH_LONG).show();
     }

 }

@Override
public void onCameraChange(CameraPosition position) {
	// TODO Auto-generated method stub
	//mTopText.setText(position.toString());
}

	
}