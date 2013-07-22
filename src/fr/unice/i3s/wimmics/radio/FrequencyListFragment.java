package fr.unice.i3s.wimmics.radio;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import fr.unice.i3s.wimmics.radio.dummy.DummyContent;
import fr.unice.i3s.wimmics.radio.model.Frequency;
import fr.unice.i3s.wimmics.radio.utilities.AjaxRequest;
import fr.unice.i3s.wimmics.radio.utilities.ApplicationConstant;
import fr.unice.i3s.wimmics.radio.utilities.JsonHelper;

/**
 * A list fragment representing a list of Frequencies. This fragment also
 * supports tablet devices by allowing list items to be given an 'activated'
 * state upon selection. This helps indicate which item is currently being
 * viewed in a {@link FrequencyDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class FrequencyListFragment extends ListFragment implements AjaxRequest {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks mCallbacks = sDummyCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public FrequencyListFragment() {
	}
	/**
	 * An array of sample (dummy) items.
	 */
	public static List<Frequency> ITEMS = new ArrayList<Frequency>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, Frequency> ITEM_MAP = new HashMap<String, Frequency>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		asyncJson(); 
//        FrequencyContent.setItems(getActivity());
//		// TODO: replace with a real list adapter.
//		setListAdapter(new ArrayAdapter<Frequency>(getActivity(),
//				android.R.layout.simple_list_item_activated_1,
//				android.R.id.text1, FrequencyContent.ITEMS));
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		mCallbacks.onItemSelected(String.valueOf(ITEMS.get(position).getId()));
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}
	
  public void asyncJson(){
        long category = getActivity().getIntent().getLongExtra(ApplicationConstant.CATEGORY_KEY,0); 
        String url = getString(R.string.base_service_url)+ "frequency/bycategory/"+category;
        System.out.println(url);
         final AQuery aq = new AQuery(getActivity());
         
         AjaxCallback<String> ac = new AjaxCallback<String>() {

             @Override
             public void callback(String url, String json, AjaxStatus status) {

                     if(json != null){
                             //successful ajax call, show status code and json content
                            // Toast.makeText(aq.getContext(), status.getCode() + ":" + json.toString(), Toast.LENGTH_LONG).show();
                    	 Type listType = new TypeToken<ArrayList<Frequency>>() {
                         }.getType();
                 	ITEMS = (ArrayList<Frequency>) JsonHelper.getListObjectFromJson(json, listType);
                 	for(Frequency frequency: ITEMS){
                 		ITEM_MAP.put(String.valueOf(frequency.getId()), frequency);
                 	}
             		setListAdapter(new ArrayAdapter<Frequency>(getActivity(),
     				android.R.layout.simple_list_item_activated_1,
      				android.R.id.text1, ITEMS));
                     
                     }else{
                    	    
                             //ajax error, show error code
                        Toast.makeText(aq.getContext(), "Error:" + status.getCode(), Toast.LENGTH_LONG).show();
                     }
             }
     };
     ac.header("Accept", "application/json");
         
	 if(aq!=null)
		 if(ac!=null)
     aq.ajax(url, String.class, ac);
		 else
			 System.out.println("Et ben bon");
	 else
		 System.out.println("Oppppsssssssss null");

}
}
