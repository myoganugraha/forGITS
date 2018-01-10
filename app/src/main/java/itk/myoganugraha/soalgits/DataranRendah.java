package itk.myoganugraha.soalgits;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import itk.myoganugraha.soalgits.Adapter.RecyclerViewDatRendahAdapter;
import itk.myoganugraha.soalgits.JSONResponse.JSONResponseDatRendah;
import itk.myoganugraha.soalgits.Model.DatRendah;
import itk.myoganugraha.soalgits.apihelper.BaseApiService;
import itk.myoganugraha.soalgits.apihelper.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataranRendah extends AppCompatActivity {

    Context mContext;
    SharedPrefManager sharedPrefManager;
    RecyclerView recyclerViewDatRendah;

    private ArrayList<DatRendah> datRendahs;
    private RecyclerViewDatRendahAdapter recyclerViewDatTinggiAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataran_rendah);

        mContext = this;
        initComponents();
        sharedPrefManager = new SharedPrefManager(this);
    }

    private void initComponents() {
        recyclerViewDatRendah = (RecyclerView)findViewById(R.id.recycler_view_datRendah);
        recyclerViewDatRendah.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerViewDatRendah.setLayoutManager(layoutManager);
        loadJSONDatRendah();

        recyclerViewDatRendah.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if(datRendahs.size()>position){
                    if(datRendahs.get(position) != null){
                        Intent intent = new Intent(mContext, DetailAlamActivty.class);
                        intent.putExtra("id_data", datRendahs.get(position).getId_data());
                        startActivity(intent);
                    }
                }
            }
        }));
    }

    private void loadJSONDatRendah() {
        BaseApiService baseApiService = RetrofitClient.getClient("http://dev.gits.id:1090/index.php/").create(BaseApiService.class);
        Call<JSONResponseDatRendah> call = baseApiService.getJSONDatRendah();

        call.enqueue(new Callback<JSONResponseDatRendah>() {
            @Override
            public void onResponse(Call<JSONResponseDatRendah> call, Response<JSONResponseDatRendah> response) {

                JSONResponseDatRendah jsonResponseMainMenu =  response.body();
                datRendahs = new ArrayList<>(Arrays.asList(jsonResponseMainMenu.getData()));
                recyclerViewDatTinggiAdapater = new RecyclerViewDatRendahAdapter(mContext, datRendahs);
                recyclerViewDatRendah.setAdapter(recyclerViewDatTinggiAdapater);
            }

            @Override
            public void onFailure(Call<JSONResponseDatRendah> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}

