package itk.myoganugraha.soalgits;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import itk.myoganugraha.soalgits.apihelper.BaseApiService;
import itk.myoganugraha.soalgits.apihelper.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.loopj.android.http.AsyncHttpClient.log;

public class Login extends AppCompatActivity {

    private Button btnLinkRegist, btnLogin;
    private EditText usernameLog, passwordLog;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        mContext = this;
        mApiService = itk.myoganugraha.soalgits.apihelper.UtlisApi.getAPIService();
        initComponents();


    }

    public void initComponents(){
        btnLinkRegist = (Button) findViewById(R.id.btnToRegister);
        btnLinkRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRegisterAct = new Intent(getApplicationContext(),Register.class);
                startActivity(toRegisterAct);
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
        mApiService.loginRequest(usernameLog.getText().toString(), passwordLog.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try{
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if(jsonRESULTS.getString("error").equalsIgnoreCase("false")){
                                    Toast.makeText(mContext, "Login Success", Toast.LENGTH_SHORT).show();
                                    String username = jsonRESULTS.getJSONObject("user").getString("nama");
                                    Intent i = new Intent(mContext, MainActivity.class);
                                    getIntent().putExtra("result_username", username);
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
}
