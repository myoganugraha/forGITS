package itk.myoganugraha.soalgits;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import itk.myoganugraha.soalgits.Model.MainMenuDataAlam;
import itk.myoganugraha.soalgits.apihelper.BaseApiService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Posting extends AppCompatActivity {


    private static final int PICK_IMAGE = 1;
    private static final int PERMISSION_REQUEST_STORAGE = 2;
    private ImageView imgThumb;
    private Button btnChoose;
    private Button btnUpload;
    Context mContext;
    Uri uri;
    Bitmap bitmap;

    boolean doubleBackToExitPressedOnce = false;
    EditText judulPost, locationPost, deskripsiPost;
    RadioGroup kategoriPost;
    ProgressDialog loading;
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    String kategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        mContext = this;
        mApiService = itk.myoganugraha.soalgits.apihelper.UtlisApi.getAPIService();
        initComponents();
    }

    private void initComponents() {
        imgThumb = (ImageView) findViewById(R.id.img_thumb);
        judulPost = (EditText)findViewById(R.id.judul);
        locationPost = (EditText)findViewById(R.id.location);
        deskripsiPost = (EditText)findViewById(R.id.deskripsi);
        kategoriPost = (RadioGroup)findViewById(R.id.radioGroupKategori);


        final int selectId1 = kategoriPost.getCheckedRadioButtonId();
        if(selectId1 == R.id.datTinggiRB){
            kategori = "1";
        }
        else if (selectId1 == R.id.datRendahRB){
            kategori = "2";
        }
        else if (selectId1 == R.id.pantaiRB){
            kategori = "3";
        }



        btnChoose = (Button) findViewById(R.id.btn_choose_photo);
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhoto();

            }
        });
        
        btnUpload = (Button) findViewById(R.id.btn_upload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kategoriPost.getCheckedRadioButtonId() == -1){
                    Toast.makeText(mContext, "Choose the Category", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(judulPost.getText().toString().trim().equalsIgnoreCase("") || locationPost.getText().toString().trim().equalsIgnoreCase("") || deskripsiPost.getText().toString().trim().equalsIgnoreCase("")   ){
                        Toast.makeText(mContext, "Fill the Blank Field", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        loading = ProgressDialog.show(mContext, null, "Please Wait", true, false);
                        requestPost();
                    }
                }
            }
        });
        
    }

    private void requestPost(){
        String base64String = ImageUtil.convert(bitmap);

        mApiService.posting(
                judulPost.getText().toString(),
                locationPost.getText().toString(),
                kategori,
                deskripsiPost.getText().toString(),
                base64String
                )
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Log.i("debug", "onResponse: SUCCESS");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if(jsonRESULTS.getString("status").equals("true")){
                                    Toast.makeText(mContext, "Thank You", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(mContext, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: FAILED");
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR >" + t.getMessage());
                        Toast.makeText(mContext, "Bad Network", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void choosePhoto() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_STORAGE);

        }else{
            openGallery();
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imgThumb.setVisibility(View.VISIBLE);
                imgThumb.setImageBitmap(bitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    openGallery();
                }
                return;
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            finish();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Back to Main Menu on Second Press", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
