package itk.myoganugraha.soalgits.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import itk.myoganugraha.soalgits.DetailAlamActivty;
import itk.myoganugraha.soalgits.Model.DetailWisata;
import itk.myoganugraha.soalgits.Model.MainMenuDataAlam;
import itk.myoganugraha.soalgits.R;
import itk.myoganugraha.soalgits.RecyclerItemClickListener;

/**
 * Created by M. Yoga Nugraha on 1/8/2018.
 */

public class RecyclerViewMainMenuAdapter extends RecyclerView.Adapter<RecyclerViewMainMenuAdapter.ViewHolder>{

    private ArrayList<MainMenuDataAlam> dataAlams;
    private Context mContext;


    public static final String BASE_IMAGE = " http://dev.gits.id:1090/index.php/api/alamku/uploads/images/";

    public RecyclerViewMainMenuAdapter(Context mContext, ArrayList<MainMenuDataAlam> dataAlams){
        this.dataAlams = dataAlams;
        this.mContext = mContext;
    }


    @Override
    public RecyclerViewMainMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mainmenu_semuadaftarwisata, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewMainMenuAdapter.ViewHolder holder, final int position) {
        MainMenuDataAlam mainMenuDataAlam1 = dataAlams.get(position);
        holder.judulWisata.setText(mainMenuDataAlam1.getTitle());

        Picasso.with(mContext).load(BASE_IMAGE + mainMenuDataAlam1.getUrl_image()).resize(120, 60)
                .into(holder.thumbnailAlamMenu);


    }

    @Override
    public int getItemCount() {

        return dataAlams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView thumbnailAlamMenu;
        private TextView judulWisata;

        public ViewHolder(View view){
            super(view);

            thumbnailAlamMenu = (ImageView)view.findViewById(R.id.thumbnailAlamMenu);
            judulWisata = (TextView)view.findViewById(R.id.judulWisata);

        }
    }




}
