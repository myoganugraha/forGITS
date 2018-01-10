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

import itk.myoganugraha.soalgits.Model.ModelPantai;
import itk.myoganugraha.soalgits.R;

/**
 * Created by M. Yoga Nugraha on 1/9/2018.
 */

public class RecyclerViewPantaiAdapter extends RecyclerView.Adapter<RecyclerViewPantaiAdapter.ViewHolder> {

    private ArrayList<ModelPantai> dataAlams;
    private Context mContext;

    public static final String BASE_IMAGE = " http://dev.gits.id:1090/index.php/api/alamku/uploads/images/";

    public RecyclerViewPantaiAdapter(Context mContext, ArrayList<ModelPantai> dataAlams){
        this.dataAlams = dataAlams;
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewPantaiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pantai, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewPantaiAdapter.ViewHolder holder, int position) {
        ModelPantai modelPantai = dataAlams.get(position);
        holder.judulWisata.setText(modelPantai.getTitle());
        holder.descWisata.setText(modelPantai.getDescription());
        Picasso.with(mContext).load(BASE_IMAGE + modelPantai.getUrl_image()).resize(120, 60)
                .into(holder.thumbnailPantai);

    }

    @Override
    public int getItemCount() {
        return dataAlams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView thumbnailPantai;
        private TextView judulWisata;
        private JustifiedTextView descWisata;

        public ViewHolder(View view){
            super(view);

            thumbnailPantai = (ImageView)view.findViewById(R.id.thumbnailPantai);
            judulWisata = (TextView)view.findViewById(R.id.titlePantai);
            descWisata = (JustifiedTextView) view.findViewById(R.id.descPantai);
        }
    }
}
