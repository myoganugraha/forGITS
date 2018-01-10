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

import itk.myoganugraha.soalgits.Model.DatRendah;
import itk.myoganugraha.soalgits.R;

/**
 * Created by M. Yoga Nugraha on 1/9/2018.
 */

public class RecyclerViewDatRendahAdapter extends RecyclerView.Adapter<RecyclerViewDatRendahAdapter.ViewHolder> {

    private ArrayList<DatRendah> dataAlams;
    private Context mContext;

    public static final String BASE_IMAGE = " http://dev.gits.id:1090/index.php/api/alamku/uploads/images/";

    public RecyclerViewDatRendahAdapter(Context mContext, ArrayList<DatRendah> dataAlams){
        this.dataAlams = dataAlams;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewDatRendahAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dataran_rendah, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewDatRendahAdapter.ViewHolder holder, int position) {
        DatRendah datRendah = dataAlams.get(position);
        holder.judulWisata.setText(datRendah.getTitle());
        holder.descWisata.setText(datRendah.getDescription());
        Picasso.with(mContext).load(BASE_IMAGE + datRendah.getUrl_image()).resize(120, 60).into(holder.thumbnailDatRendah);

    }

    @Override
    public int getItemCount() {
        return dataAlams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView thumbnailDatRendah;
        private TextView judulWisata;
        private JustifiedTextView descWisata;

        public ViewHolder(View view){
            super(view);

            thumbnailDatRendah = (ImageView)view.findViewById(R.id.thumbnailDatRendah);
            judulWisata = (TextView)view.findViewById(R.id.titleDatRendah);
            descWisata = (JustifiedTextView) view.findViewById(R.id.descDatRendah);
        }
    }
}
