package itk.myoganugraha.soalgits.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import itk.myoganugraha.soalgits.Model.MainMenu;
import itk.myoganugraha.soalgits.R;

/**
 * Created by M. Yoga Nugraha on 1/7/2018.
 */

public class RecyclerViewMenuAdapater extends RecyclerView.Adapter<RecyclerViewMenuAdapater.CustomViewHolder>{
    private List<MainMenu> mainMenuList;
    private Context mContext;

    public RecyclerViewMenuAdapater(Context mContext, List<MainMenu> mainMenuList){
        this.mContext = mContext;
        this.mainMenuList = mainMenuList;
    }


    @Override
    public RecyclerViewMenuAdapater.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_cardview, parent, false);

        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewMenuAdapater.CustomViewHolder holder, int position) {
        MainMenu menu = mainMenuList.get(position);
        holder.textView.setText(String.valueOf(menu.getMenu()));
        holder.imageView.setImageResource(menu.getIcon());


    }

    @Override
    public int getItemCount() {
        return mainMenuList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{
        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View view){
            super(view);
            this.imageView = (ImageView)view.findViewById(R.id.icon);
            this.textView = (TextView)view.findViewById(R.id.menu);
        }
    }

}
