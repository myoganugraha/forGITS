package itk.myoganugraha.soalgits;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import itk.myoganugraha.soalgits.Adapter.RecyclerViewMainMenuAdapter;
import itk.myoganugraha.soalgits.Adapter.RecyclerViewTigaMenuAdapter;
import itk.myoganugraha.soalgits.JSONResponse.JSONResponseMainMenu;
import itk.myoganugraha.soalgits.Model.MainMenuDataAlam;
import itk.myoganugraha.soalgits.Model.MainMenuTigaMenu;
import itk.myoganugraha.soalgits.apihelper.BaseApiService;
import itk.myoganugraha.soalgits.apihelper.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    boolean doubleBackToExitPressedOnce = false;
    Context mContext;
    SharedPrefManager sharedPrefManager;
    FloatingActionButton fab;

    private RecyclerView recyclerViewTigaMenu, recyclerViewMainMenu;
    private GridLayoutManager mGridLayoutManager;

    private List<MainMenuTigaMenu> mainMenuTigaMenuList;
    private RecyclerViewTigaMenuAdapter recyclerViewTigaMenuAdapter;

    private ArrayList<MainMenuDataAlam> mainMenuDataAlam;
    private RecyclerViewMainMenuAdapter recyclerViewMainMenuAdapter;

    int logos[] = {R.drawable.dataran_tinggi, R.drawable.dataran_rendah, R.drawable.pantai};
    String listMenu[] = {"Dataran Tinggi", "Dataran Rendah", "Pantai"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initComponents();
        sharedPrefManager = new SharedPrefManager(this);
    }

    private void initComponents() {

        loadFAB();
        loadRecyclerViewTigaMenu();
        loadRecyclerViewMenu();

    }

    private void loadFAB() {
        fab = (FloatingActionButton) findViewById(R.id.FAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPosting = new Intent(mContext,Posting.class);
                startActivity(toPosting);
            }
        });
    }


    private void loadRecyclerViewTigaMenu() {
        recyclerViewTigaMenu = (RecyclerView)findViewById(R.id.recycler_view_main_menu_tiga);
        if(recyclerViewTigaMenu != null){
            recyclerViewTigaMenu.setHasFixedSize(true);
        }

        mGridLayoutManager = new GridLayoutManager(mContext, 3);
        recyclerViewTigaMenu.setLayoutManager(mGridLayoutManager);

        mainMenuTigaMenuList = new ArrayList<>();
        for (int i = 0; i< listMenu.length; i++){
            MainMenuTigaMenu mainMenuTigaMenu = new MainMenuTigaMenu(logos[i], listMenu[i]);
            mainMenuTigaMenuList.add(mainMenuTigaMenu);
        }
        recyclerViewTigaMenuAdapter = new RecyclerViewTigaMenuAdapter(mContext, mainMenuTigaMenuList);

        recyclerViewTigaMenu.setAdapter(recyclerViewTigaMenuAdapter);
        recyclerViewTigaMenuAdapter.notifyDataSetChanged();

        recyclerViewTigaMenu.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                if(position==0){
                    Intent intent = new Intent(mContext, DataranTinggi.class);
                    startActivity(intent);
                }
                else if(position==1){
                    Intent intent = new Intent(mContext, DataranRendah.class);
                    startActivity(intent);
                }
                else if(position==2){
                    Intent intent = new Intent(mContext, Pantai.class);
                    startActivity(intent);
                }
            }
        }));
    }



    private void loadRecyclerViewMenu() {
        recyclerViewMainMenu = (RecyclerView)findViewById(R.id.recycler_view_main_menu_dataalam);
        recyclerViewMainMenu.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerViewMainMenu.setLayoutManager(layoutManager);
        loadJSONMainMenu();
    }

    private void loadJSONMainMenu() {
        BaseApiService baseApiService = RetrofitClient.getClient("http://dev.gits.id:1090/index.php/").create(BaseApiService.class);
        Call<JSONResponseMainMenu> call = baseApiService.getJSONDataAlam();

        call.enqueue(new Callback<JSONResponseMainMenu>() {
            @Override
            public void onResponse(Call<JSONResponseMainMenu> call, Response<JSONResponseMainMenu> response) {

                JSONResponseMainMenu jsonResponseMainMenu =  response.body();
                mainMenuDataAlam = new ArrayList<>(Arrays.asList(jsonResponseMainMenu.getData()));
                recyclerViewMainMenuAdapter = new RecyclerViewMainMenuAdapter(mContext, mainMenuDataAlam);
                recyclerViewMainMenu.setAdapter(recyclerViewMainMenuAdapter);
            }

            @Override
            public void onFailure(Call<JSONResponseMainMenu> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.logout:
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                Toast.makeText(mContext, "You've Been Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, Login.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Exit on Second Press", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
