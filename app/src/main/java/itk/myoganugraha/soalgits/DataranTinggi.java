package itk.myoganugraha.soalgits;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import itk.myoganugraha.soalgits.Adapter.RecyclerViewDatTinggiAdapter;
import itk.myoganugraha.soalgits.JSONResponse.JSONResponseDatTinggi;
import itk.myoganugraha.soalgits.Model.DatTinggi;
import itk.myoganugraha.soalgits.apihelper.BaseApiService;
import itk.myoganugraha.soalgits.apihelper.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.ArrayList;
import java.util.Arrays;


public class DataranTinggi extends AppCompatActivity {

    Context mContext;
    SharedPrefManager sharedPrefManager;
    RecyclerView recyclerViewDatTinggi;

    private ArrayList<DatTinggi> datTinggis;
    private RecyclerViewDatTinggiAdapter recyclerViewDatTinggiAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataran_tinggi);

        mContext = this;
        initComponents();
        sharedPrefManager = new SharedPrefManager(this);

    }

    private void initComponents() {

        recyclerViewDatTinggi = (RecyclerView)findViewById(R.id.recycler_view_datTinggi);
        recyclerViewDatTinggi.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerViewDatTinggi.setLayoutManager(layoutManager);
        loadJSONDatTinggi();
    }

    private void loadJSONDatTinggi() {
        BaseApiService baseApiService = RetrofitClient.getClient("http://dev.gits.id:1090/index.php/").create(BaseApiService.class);
        Call<JSONResponseDatTinggi> call = baseApiService.getJSONDatTinggi();

        call.enqueue(new Callback<JSONResponseDatTinggi>() {
            @Override
            public void onResponse(Call<JSONResponseDatTinggi> call, Response<JSONResponseDatTinggi> response) {

                JSONResponseDatTinggi jsonResponseMainMenu =  response.body();
                datTinggis= new ArrayList<>(Arrays.asList(jsonResponseMainMenu.getData()));
                recyclerViewDatTinggiAdapter = new RecyclerViewDatTinggiAdapter(mContext, datTinggis);
                recyclerViewDatTinggi.setAdapter(recyclerViewDatTinggiAdapter);
            }

            @Override
            public void onFailure(Call<JSONResponseDatTinggi> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}
