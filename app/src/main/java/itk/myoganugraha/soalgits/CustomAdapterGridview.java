package itk.myoganugraha.soalgits;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by M. Yoga Nugraha on 1/7/2018.
 */

public class CustomAdapterGridview extends BaseAdapter{
    Context mContext;
    int logos[];
    String listMenus[];
    LayoutInflater inflter;

    public CustomAdapterGridview(Context applicationContext, int[] logos, String[] listMenu){
        this.mContext = applicationContext;
        this.logos = logos;
        this.listMenus = listMenu;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount(){
        return logos.length;
    }

    @Override
    public Object getItem(int i ){
        return null;
    }

    @Override
    public long getItemId(int i ){
        return 0;
    }

    public class Holder{
        TextView listMenu;
        ImageView icon;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        view = inflter.inflate(R.layout.activity_main_gridview, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        TextView listMenu = (TextView) view.findViewById(R.id.listMenu);
        icon.setImageResource(logos[i]);
        listMenu.setText(listMenus[i]);
        return view;
    }


}
