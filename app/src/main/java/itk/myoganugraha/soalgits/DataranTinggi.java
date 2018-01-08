package itk.myoganugraha.soalgits;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import itk.myoganugraha.soalgits.apihelper.BaseApiService;
import itk.myoganugraha.soalgits.apihelper.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataranTinggi extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataran_tinggi);

    }

}
