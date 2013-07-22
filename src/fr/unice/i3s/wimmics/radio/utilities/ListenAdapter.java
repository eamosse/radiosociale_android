package fr.unice.i3s.wimmics.radio.utilities;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;

import fr.unice.i3s.wimmics.radio.R;
import fr.unice.i3s.wimmics.radio.utilities.MAdapter.ViewHolder;

public class ListenAdapter extends BaseAdapter
{
    //private ArrayList<String> listCountry;
    private List<ActionItem> list;
    //private Activity activity;
    AQuery aq; 
    LayoutInflater inflator; 
 
    public ListenAdapter(List<ActionItem> list, LayoutInflater inflator) {
        super();
        this.list = list;
        //this.activity = activity;
        //aq = new AQuery(activity);
        this.inflator= inflator;
    }
 
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }
 
    @Override
    public ActionItem getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
 
    public static class ViewHolder
    {
        public ImageView imgViewFlag;
        public TextView txtViewTitle;
        public TextView txtDescription;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view;
       // LayoutInflater inflator = activity.getLayoutInflater();
 
        if(convertView==null)
        {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.notif_line, null);
 
            view.txtViewTitle = (TextView) convertView.findViewById(R.id.titre);
            view.txtDescription = (TextView) convertView.findViewById(R.id.description);
            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.img);
 
            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }
        ActionItem category = list.get(position);
        ImageOptions options = new ImageOptions();
        options.round = 15;
        //System.out.println("Object " + category.getName());
        view.txtViewTitle.setText(category.getTitle());
        //String url = activity.getString(R.string.base_url) + category.getImage();
       // System.out.println(category + " ==> " + url);
        view.imgViewFlag.setImageDrawable(category.getIcon());
       // aq.id(view.imgViewFlag).image(url, options);
        return convertView;
    }
}
