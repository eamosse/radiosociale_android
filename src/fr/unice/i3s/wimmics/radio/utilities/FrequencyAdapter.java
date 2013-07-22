
package fr.unice.i3s.wimmics.radio.utilities;

import java.util.ArrayList;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;

import fr.unice.i3s.wimmics.radio.R;
import fr.unice.i3s.wimmics.radio.model.Frequency;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class FrequencyAdapter extends BaseAdapter
{
    //private ArrayList<String> listCountry;
    private ArrayList<Frequency> list;
    private Activity activity;
    AQuery aq; 
 
    public FrequencyAdapter(Activity activity,ArrayList<Frequency> list) {
        super();
        this.list = list;
        this.activity = activity;
        aq = new AQuery(activity);
    }
 
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }
 
    @Override
    public Frequency getItem(int position) {
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
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();
 
        if(convertView==null)
        {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.gridview_row, null);
 
            view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView1);
            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.imageView1);
 
            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }
        Frequency frequency = list.get(position);
        ImageOptions options = new ImageOptions();
        options.round = 15;
        System.out.println("Object " + frequency.getName());
        view.txtViewTitle.setText(frequency.getName());
        String url = activity.getString(R.string.base_url) + frequency.getImage();
        System.out.println(frequency + " ==> " + url);
        aq.id(view.imgViewFlag).image(url, options);
        return convertView;
    }
}
