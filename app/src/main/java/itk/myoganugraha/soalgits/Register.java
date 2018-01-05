package itk.myoganugraha.soalgits;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import itk.myoganugraha.soalgits.apihelper.BaseApiService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    Button signUp;
    EditText firstname, lastname, username, password, bday, phone;
    Calendar myCalendar = Calendar.getInstance();
    RadioGroup genderRadioGroup;
    ProgressDialog loading;
    String gender;

    Context mContext;
    BaseApiService mApiService;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext = this;
        mApiService = itk.myoganugraha.soalgits.apihelper.UtlisApi.getAPIService();
        initComponents();
    }

    private void initComponents(){
        firstname = (EditText) findViewById(R.id.firstName);
        lastname = (EditText) findViewById(R.id.lastName);
        username = (EditText) findViewById(R.id.usernameReg);
        password = (EditText) findViewById(R.id.passwordReg);
        bday = (EditText) findViewById(R.id.bday);
        genderRadioGroup = (RadioGroup) findViewById(R.id.radioGroupGender);
        phone = (EditText) findViewById(R.id.phoneNumber);
        signUp = (Button) findViewById(R.id.btnRegister);

        final int selectId = genderRadioGroup.getCheckedRadioButtonId();
        if(selectId == R.id.lakiLakiRB){
            gender = "Female";
        }
        else{
            gender = "Male";
        }



        bday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear=mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        updateLabel();
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Please Wait", true, false);
                requestRegister();
            }
        });

    }

    private void requestRegister(){
        mApiService.registerRequest(
                firstname.getText().toString(),
                lastname.getText().toString(),
                username.getText().toString(),
                password.getText().toString(),
                bday.getText().toString(),
                gender,
                phone.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Log.i("debug", "onResponse: SUCCESS");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if(jsonRESULTS.getString("status").equals("true")){
                                    Toast.makeText(mContext, "REGISTRATION SUCCESS", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(mContext, Login.class);
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

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        bday.setText(sdf.format(myCalendar.getTime()));
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
