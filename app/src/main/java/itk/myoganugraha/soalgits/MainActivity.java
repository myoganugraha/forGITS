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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import itk.myoganugraha.soalgits.Adapter.RecyclerViewMenuAdapater;
import itk.myoganugraha.soalgits.Model.MainMenu;

public class MainActivity extends AppCompatActivity {


    boolean doubleBackToExitPressedOnce = false;
    private Button btnLogout;
    Context mContext;
    SharedPrefManager sharedPrefManager;
    FloatingActionButton fab;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager mGridLayoutManager;

    private List<MainMenu> mainMenuList;
    private RecyclerViewMenuAdapater recyclerViewMenuAdapater;

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

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_main_menu);
        if(recyclerView != null){
            recyclerView.setHasFixedSize(true);
        }

        mGridLayoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(mGridLayoutManager);


        mainMenuList = new ArrayList<>();
        for (int i = 0; i< listMenu.length; i++){
            MainMenu mainMenu = new MainMenu(logos[i], listMenu[i]);
            mainMenuList.add(mainMenu);
        }

        recyclerViewMenuAdapater = new RecyclerViewMenuAdapater(mContext, mainMenuList);

        recyclerView.setAdapter(recyclerViewMenuAdapater);
        recyclerViewMenuAdapater.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
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

        fab = (FloatingActionButton) findViewById(R.id.FAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPosting = new Intent(mContext,Posting.class);
                startActivity(toPosting);
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
