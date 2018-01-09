package itk.myoganugraha.soalgits.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import itk.myoganugraha.soalgits.Model.MainMenuTigaMenu;
import itk.myoganugraha.soalgits.R;

/**
 * Created by M. Yoga Nugraha on 1/7/2018.
 */

public class RecyclerViewTigaMenuAdapter extends RecyclerView.Adapter<RecyclerViewTigaMenuAdapter.CustomViewHolder>{
    private List<MainMenuTigaMenu> mainMenuTigaMenuList;
    private Context mContext;

    public RecyclerViewTigaMenuAdapter(Context mContext, List<MainMenuTigaMenu> mainMenuTigaMenuList){
        this.mContext = mContext;
        this.mainMenuTigaMenuList = mainMenuTigaMenuList;
    }


    @Override
    public RecyclerViewTigaMenuAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mainmenu_tigamenu, parent, false);

        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewTigaMenuAdapter.CustomViewHolder holder, int position) {
        MainMenuTigaMenu menu = mainMenuTigaMenuList.get(position);
        holder.textView.setText(String.valueOf(menu.getMenu()));
        holder.imageView.setImageResource(menu.getIcon());


    }

    @Override
    public int getItemCount() {
        return mainMenuTigaMenuList.size();
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
