package fr.unice.i3s.wimmics.radio.utilities;

import java.util.ArrayList;

import com.androidquery.AQuery;
import com.androidquery.callback.ImageOptions;

import fr.unice.i3s.wimmics.radio.R;
import fr.unice.i3s.wimmics.radio.model.Category;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class GridviewAdapter extends BaseAdapter
{
    //private ArrayList<String> listCountry;
    private ArrayList<Category> list;
    private Activity activity;
    AQuery aq; 
 
    public GridviewAdapter(Activity activity,ArrayList<Category> list) {
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
    public Category getItem(int position) {
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
        Category category = list.get(position);
        ImageOptions options = new ImageOptions();
        options.round = 15;
        System.out.println("Object " + category.getName());
        view.txtViewTitle.setText(category.getName());
        String url =activity.getString(R.string.base_url) + category.getImage();
        System.out.println(category + " ==> " + url);
        aq.id(view.imgViewFlag).image(url, options);
        return convertView;
    }
}
