package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class NewDataActivity extends AppCompatActivity {
    private TextInputEditText site_url;
    private TextInputEditText username;
    private TextInputEditText password;
    String user,site,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);
        site_url=findViewById(R.id.site_url_input);
        username=findViewById(R.id.username_input);
        password=findViewById(R.id.password_input);

        final MaterialButton insertbutton=findViewById(R.id.insertbutton);
        insertbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent=new Intent();
                if(TextUtils.isEmpty(site_url.getText())||TextUtils.isEmpty(username.getText())||TextUtils.isEmpty(password.getText()))
                {
                    setResult(RESULT_CANCELED,replyIntent);
                }
                else{
                    user=username.getText().toString();
                    site=site_url.getText().toString();
                    pass=password.getText().toString();
                    replyIntent.putExtra("site_url",site);
                    replyIntent.putExtra("username",user);
                    replyIntent.putExtra("password",pass);
                    setResult(1,replyIntent);
                }
                finish();
            }
        });
        final MaterialButton updatebutton=findViewById(R.id.updatebutton);
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent=new Intent();
                if(TextUtils.isEmpty(site_url.getText())||TextUtils.isEmpty(username.getText())||TextUtils.isEmpty(password.getText()))
                {
                    setResult(RESULT_CANCELED,replyIntent);
                }
                else{
                    user=username.getText().toString();
                    site=site_url.getText().toString();
                    pass=password.getText().toString();
                    replyIntent.putExtra("site_url",site);
                    replyIntent.putExtra("username",user);
                    replyIntent.putExtra("password",pass);
                    setResult(2,replyIntent);
                }
                finish();
            }
        });
    }
}