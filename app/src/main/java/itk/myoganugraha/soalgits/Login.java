package itk.myoganugraha.soalgits;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import itk.myoganugraha.soalgits.apihelper.BaseApiService;
import itk.myoganugraha.soalgits.apihelper.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.loopj.android.http.AsyncHttpClient.log;

public class Login extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    private Button btnLinkRegist, btnLogin;
    private EditText usernameLog, passwordLog;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;
    String StringAwal, StringHasil;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        mContext = this;
        mApiService = UtlisApi.getAPIService();
        initComponents();




    }

    public void initComponents(){
        btnLinkRegist = (Button) findViewById(R.id.btnToRegister);
        btnLinkRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRegisterAct = new Intent(getApplicationContext(),Register.class);
                startActivity(toRegisterAct);
                finish();
            }
        });


        usernameLog = (EditText) findViewById(R.id.usernameLog);
        passwordLog = (EditText) findViewById(R.id.passwordLog);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Please Wait", true, false);
                requestLogin();
            }
        });
    }


    public void requestLogin(){
        StringAwal = passwordLog.getText().toString();
        StringHasil = md5(StringAwal);

        mApiService.loginRequest(usernameLog.getText().toString(), StringHasil)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try{
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if(jsonRESULTS.getString("status").equals("true")){
                                    Toast.makeText(mContext, "Login Success", Toast.LENGTH_SHORT).show();
                                    //String username = jsonRESULTS.getJSONObject("user").getString("username");
                                    Intent i = new Intent(mContext, MainActivity.class);
                                    //getIntent().putExtra("result_nama", username);
                                    startActivity(i);
                                }
                                else {
                                    String error_message = jsonRESULTS.getString("error_message");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        log.e("debug", "onFailure: ERROR >" + t.toString());
                        loading.dismiss();
                    }
                });
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
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
