package itk.myoganugraha.soalgits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    private Button btnLinkRegist, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        btnLinkRegist = (Button) findViewById(R.id.btnToRegister);
        btnLinkRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRegisterAct = new Intent(getApplicationContext(),Register.class);
                startActivity(toRegisterAct);
            }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMainAct = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toMainAct);
            }
        });
    }
}
