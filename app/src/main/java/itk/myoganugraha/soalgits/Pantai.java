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

import itk.myoganugraha.soalgits.Adapter.RecyclerViewPantaiAdapter;
import itk.myoganugraha.soalgits.JSONResponse.JSONResponsePantai;
import itk.myoganugraha.soalgits.Model.ModelPantai;
import itk.myoganugraha.soalgits.apihelper.BaseApiService;
import itk.myoganugraha.soalgits.apihelper.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pantai extends AppCompatActivity {

    Context mContext;
    SharedPrefManager sharedPrefManager;
    RecyclerView recyclerViewPantai;

    private ArrayList<ModelPantai> pantais;
    private RecyclerViewPantaiAdapter recyclerViewPantaiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantai);

        mContext = this;
        initComponents();
        sharedPrefManager = new SharedPrefManager(this);
    }

    private void initComponents() {

        recyclerViewPantai = (RecyclerView)findViewById(R.id.recycler_view_pantai);
        recyclerViewPantai.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerViewPantai.setLayoutManager(layoutManager);
        recyclerViewPantai.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if(pantais.size()>position){
                    if(pantais.get(position) != null){
                        Intent intent = new Intent(mContext, DetailAlamActivty.class);
                        intent.putExtra("id_data", pantais.get(position).getId_data());
                        startActivity(intent);
                    }
                }
            }
        }));

        loadJSONPantai();
    }

    private void loadJSONPantai() {
        BaseApiService baseApiService = RetrofitClient.getClient("http://dev.gits.id:1090/index.php/").create(BaseApiService.class);
        Call<JSONResponsePantai> call = baseApiService.getJSONPantai();

        call.enqueue(new Callback<JSONResponsePantai>() {
            @Override
            public void onResponse(Call<JSONResponsePantai> call, Response<JSONResponsePantai> response) {

                JSONResponsePantai jsonResponseMainMenu =  response.body();
                pantais = new ArrayList<>(Arrays.asList(jsonResponseMainMenu.getData()));
                recyclerViewPantaiAdapter = new RecyclerViewPantaiAdapter(mContext, pantais);
                recyclerViewPantai.setAdapter(recyclerViewPantaiAdapter);
            }

            @Override
            public void onFailure(Call<JSONResponsePantai> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
