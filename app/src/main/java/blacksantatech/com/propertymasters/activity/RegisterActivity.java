package blacksantatech.com.propertymasters.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import blacksantatech.com.propertymasters.R;
import blacksantatech.com.propertymasters.network.Uploader;

public class RegisterActivity extends AppCompatActivity
{
    private AppCompatEditText firstname, lastname, email, phone, password, password_confirm;
    private AppCompatButton register, login;

    private String user_firstname, user_lastname, user_email, user_phone, user_password, user_password_confirm, user_token;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialize User Input Areas
        firstname = findViewById(R.id.user_firstname);
        lastname = findViewById(R.id.user_lastname);
        email = findViewById(R.id.user_email);
        phone = findViewById(R.id.user_phone);
        password = findViewById(R.id.user_password);
        password_confirm = findViewById(R.id.password_confirm);

        //Initialize Buttons ie login and register buttons.
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        progressDialog = new ProgressDialog(this);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Move to Login page from register page.
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_firstname = firstname.getText().toString().trim();
                user_lastname = lastname.getText().toString().trim();
                user_email = email.getText().toString().trim();
                user_phone = phone.getText().toString().trim();
                user_password = password.getText().toString().trim();
                user_password_confirm = password_confirm.getText().toString().trim();
              ///  user_token = FirebaseInstanceId.getInstance().getToken();

                if(validateEmail() && validatePhone() && validatePassword() && validateConfirmPassword() && matchPassword() && validateFName() && validateLName()){
                    //take to server
                   // Toast.makeText(getApplicationContext(), "To Server Now", Toast.LENGTH_LONG).show();

                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    Uploader uploader = new Uploader(RegisterActivity.this, progressDialog);
                    user_token  = FirebaseInstanceId.getInstance().getToken();
                    uploader.register(user_firstname, user_lastname, user_email,user_phone,user_password);

                }else{
                    if(!validateEmail()){
                        email.setError("Field cannot be Empty");
                    }
                    if(!validatePhone()){
                        phone.setError("Field cannot be Empty");
                    }
                    if(!validatePassword()){
                        password.setError("Field cannot be Empty");
                    }
                    if(!validateConfirmPassword()){
                        password_confirm.setError("Field cannot be Empty");
                    }
                    if(!matchPassword()){
                        Toast.makeText(RegisterActivity.this, "Passwords must match.", Toast.LENGTH_SHORT).show();
                    }
                    if(!validateFName()){
                        firstname.setError("Field cannot be Empty.");
                    }
                    if(!validateLName()){
                        lastname.setError("Field cannot be Empty.");
                    }
                }
            }
        });


    }


    private boolean validateEmail(){
        if(!(user_email.isEmpty() || user_email.equals(""))){
            return true;
        }else {
            return false;
        }
    }

    private boolean validateFName(){
        if(!(user_firstname.isEmpty() || user_firstname.equals(""))){
            return true;
        }else {
            return false;
        }
    }

    private boolean validateLName(){
        if(!(user_lastname.isEmpty() || user_lastname.equals(""))){
            return true;
        }else {
            return false;
        }
    }

    private boolean validatePhone(){
        if(!(user_phone.isEmpty() || user_phone.equals(""))){
            return true;
        }else {
            return false;
        }
    }

    private boolean validatePassword(){
        if(!(user_password.isEmpty() || user_password.equals(""))){
            return true;
        }else {
            return false;
        }
    }

    private boolean validateConfirmPassword(){
        if(!(user_password_confirm.isEmpty() || user_password_confirm.equals(""))){
            return true;
        }else {
            return false;
        }
    }

    private boolean matchPassword(){
        if(user_password.equalsIgnoreCase(user_password_confirm) || user_password.equals(user_password_confirm)){
            return true;
        }else {
            return false;
        }
    }
}
