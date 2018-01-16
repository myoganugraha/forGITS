package itk.myoganugraha.soalgits.Adapter;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesgood.views.JustifiedTextView;

import java.util.ArrayList;

import itk.myoganugraha.soalgits.Model.DetailWisata;
import itk.myoganugraha.soalgits.R;

/**
 * Created by M. Yoga Nugraha on 1/10/2018.
 */

public class RecyclerViewDetailAdapter extends RecyclerView.Adapter<RecyclerViewDetailAdapter.ViewHolder> {

    private ArrayList<DetailWisata> detailWisatas;
    private Context mContext;

    public static final String BASE_IMAGE = " http://dev.gits.id:1090/index.php/api/alamku/uploads/images/";

    public RecyclerViewDetailAdapter(Context mContext, ArrayList<DetailWisata> detailWisatas){
        this.detailWisatas = detailWisatas;
        this.mContext = mContext;
    }


    @Override
    public RecyclerViewDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_detail, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DetailWisata detailWisata = detailWisatas.get(position);

        holder.kategori.setText(detailWisata.getCategory());
        holder.locDetail.setText(detailWisata.getLocation());
        holder.descDetail.setText(detailWisata.getDescription());

        //todo fix title dan gambar beda layout
        //beda layout holder.collapsingToolbarLayout.setTitle(detailWisata.getTitle().toString());
        //Picasso.with(mContext).load(BASE_IMAGE + detailWisata.getUrl_image()).resize(120, 60).into(holder.headerDetail1);
    }

    @Override
    public int getItemCount() {
        return detailWisatas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView kategori, locDetail;
        private JustifiedTextView descDetail;

        public ViewHolder(View view){
            super(view);

            kategori = (TextView) view.findViewById(R.id.kategoriDetail);
            descDetail = (JustifiedTextView) view.findViewById(R.id.descDetail);
            locDetail = (TextView) view.findViewById(R.id.locDetail);

        }
    }


}