package itk.myoganugraha.soalgits;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    boolean doubleBackToExitPressedOnce = false;
    private Button btnLogout;
    Context mContext;
    SharedPrefManager sharedPrefManager;
    FloatingActionButton fab;
    GridView simpleGrid;
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

        simpleGrid = (GridView) findViewById(R.id.simpleGridView);
        CustomAdapterGridview customAdapterGridview = new CustomAdapterGridview(mContext, logos, listMenu);
        simpleGrid.setAdapter(customAdapterGridview);

        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent = new Intent(mContext, DataranTinggi.class);
                    startActivity(intent);
                }
                else if(i==1){
                    Intent intent = new Intent(mContext, DataranRendah.class);
                    startActivity(intent);
                }
                else if(i==2){
                    Intent intent = new Intent(mContext, Pantai.class);
                    startActivity(intent);
                }
            }
        });


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
