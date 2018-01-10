package itk.myoganugraha.soalgits.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesgood.views.JustifiedTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import itk.myoganugraha.soalgits.Model.DatTinggi;
import itk.myoganugraha.soalgits.R;

/**
 * Created by M. Yoga Nugraha on 1/9/2018.
 */

public class RecyclerViewDatTinggiAdapter extends RecyclerView.Adapter<RecyclerViewDatTinggiAdapter.ViewHolder> {

    private ArrayList<DatTinggi> dataAlams;
    private Context mContext;

    public static final String BASE_IMAGE = " http://dev.gits.id:1090/index.php/api/alamku/uploads/images/";

    public RecyclerViewDatTinggiAdapter(Context mContext, ArrayList<DatTinggi> dataAlams){
        this.dataAlams = dataAlams;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewDatTinggiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dataran_tinggi, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewDatTinggiAdapter.ViewHolder holder, int position) {
        DatTinggi datTinggi = dataAlams.get(position);
        holder.descWisata.setText(datTinggi.getDescription());
        holder.judulWisata.setText(datTinggi.getTitle());
        Picasso.with(mContext).load(BASE_IMAGE + datTinggi.getUrl_image()).resize(120, 60).into(holder.thumbnailDatTinggi);

    }

    @Override
    public int getItemCount() {
        return dataAlams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView thumbnailDatTinggi;
        private TextView judulWisata;
        private JustifiedTextView descWisata;

        public ViewHolder(View view){
            super(view);

            thumbnailDatTinggi = (ImageView)view.findViewById(R.id.thumbnailDatTinggi);
            judulWisata = (TextView)view.findViewById(R.id.titleDatTinggi);
            descWisata = (JustifiedTextView) view.findViewById(R.id.descDatTinggi);
        }
    }
}
