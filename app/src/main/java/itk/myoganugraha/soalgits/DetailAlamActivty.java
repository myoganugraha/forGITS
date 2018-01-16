package itk.myoganugraha.soalgits;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import itk.myoganugraha.soalgits.Adapter.RecyclerViewDetailAdapter;
import itk.myoganugraha.soalgits.JSONResponse.JSONResponseDetail;
import itk.myoganugraha.soalgits.Model.DetailWisata;
import itk.myoganugraha.soalgits.apihelper.BaseApiService;
import itk.myoganugraha.soalgits.apihelper.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAlamActivty extends AppCompatActivity {

    Context mContext;
    SharedPrefManager sharedPrefManager;
    RecyclerView recyclerViewDetail;

    private ArrayList<DetailWisata> detailWisatas;
    private RecyclerViewDetailAdapter recyclerViewDetailAdapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_activty);


        mContext = this;
        initComponents();
        sharedPrefManager = new SharedPrefManager(this);
    }

    private void initComponents() {
        recyclerViewDetail = (RecyclerView)findViewById(R.id.recycler_view_detail);
        recyclerViewDetail.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerViewDetail.setLayoutManager(layoutManager);



        loadJSONDetail();
    }

    private void loadJSONDetail() {
        BaseApiService baseApiService = RetrofitClient.getClient("http://dev.gits.id:1090/index.php/").create(BaseApiService.class);
        Call<JSONResponseDetail> call = baseApiService.getJSONDetail();

        call.enqueue(new Callback<JSONResponseDetail>() {
            @Override
            public void onResponse(Call<JSONResponseDetail> call, Response<JSONResponseDetail> response) {

                JSONResponseDetail jsonResponseMainMenu =  response.body();
                detailWisatas = new ArrayList<>(Arrays.asList(jsonResponseMainMenu.getData()));
                recyclerViewDetailAdapter = new RecyclerViewDetailAdapter(mContext, detailWisatas);
                recyclerViewDetail.setAdapter(recyclerViewDetailAdapter);

            }

            @Override
            public void onFailure(Call<JSONResponseDetail> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
