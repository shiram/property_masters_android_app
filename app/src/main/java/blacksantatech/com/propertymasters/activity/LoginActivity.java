package blacksantatech.com.propertymasters.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import blacksantatech.com.propertymasters.R;
import blacksantatech.com.propertymasters.network.Receiver;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton login_button, register_button;
    AppCompatTextView request_password;
    AppCompatEditText user_email, user_password;

    String email, password;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button = findViewById(R.id.login);
        register_button = findViewById(R.id.register);
        request_password = findViewById(R.id.request_password);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);

        progressDialog = new ProgressDialog(this);



        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = user_email.getText().toString().trim();
                password = user_password.getText().toString().trim();

                if(validateEmail() && validatePassword()) {
                    progressDialog.show();
                    Receiver receiver = new Receiver(LoginActivity.this, progressDialog);
                    receiver.userLogin(email, password);
                }else{
                    if(!validateEmail()){
                        user_email.setError("Field cannot be Empty");
                    }
                    if(!validatePassword()){
                        user_password.setError("Field cannot be Empty");
                    }
                }

            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register_intent);
            }
        });

    }


    private boolean validateEmail(){
        if(!(email.isEmpty() || email.equals(""))){
            return true;
        }else {
            return false;
        }
    }


    private boolean validatePassword(){
        if(!(password.isEmpty() || password.equals(""))){
            return true;
        }else {
            return false;
        }
    }
}
