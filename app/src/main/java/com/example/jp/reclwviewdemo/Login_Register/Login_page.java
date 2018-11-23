package com.example.jp.reclwviewdemo.Login_Register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.jp.reclwviewdemo.R;
import com.example.jp.reclwviewdemo.activity.MainActivity;

import java.util.HashMap;
import java.util.Map;


public class Login_page extends AppCompatActivity {


    EditText roll,pass;
    TextView textViewLinkRegister;
    Button login;
    String rollno,password;
    String regurl="https://zarnamak24.000webhostapp.com/login.php";
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;


    public static final String KEY_USERNAME = "username";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        roll=(EditText)findViewById(R.id.rollno);
        pass=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.btnlogin);
      textViewLinkRegister=(TextView)findViewById(R.id.textViewLinkRegister);

      textViewLinkRegister.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
          Intent Intentlogin=new Intent(getApplicationContext(),Register.class);
          startActivity(Intentlogin);
        }
      });
        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                rollno=roll.getText().toString();
                password=pass.getText().toString();
              if(TextUtils.isEmpty(rollno)){
                Toast.makeText(Login_page.this, "plz Enter Rollno", Toast.LENGTH_SHORT).show();
              }


              if(TextUtils.isEmpty(password)){
                Toast.makeText(Login_page.this, "plz enter password", Toast.LENGTH_SHORT).show();
              }
              if(isValidPassword(password)==false)
              {
                Toast.makeText(Login_page.this, "plz Enter more then 6 digit", Toast.LENGTH_SHORT).show();
              }
                StringRequest request=new StringRequest(Request.Method.POST, regurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString(KEY_USERNAME, rollno );

                            editor.commit();
                            Toast.makeText(Login_page.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                            Intent i=new Intent(Login_page.this,MainActivity.class);
                            startActivity(i);

                        }else {
                            Toast.makeText(Login_page.this, "Unsuccessfull", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login_page.this, "Error"+error, Toast.LENGTH_SHORT).show();
                    }
                })


                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> param=new HashMap<>();


                        param.put("rollno",rollno);
                        param.put("password",password);

                        Log.e("Data",">>>>>>>>>>>>>>"+rollno+password);

                        return param;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(Login_page.this);
                queue.add(request);

            }
          private boolean isValidPassword(String pass) {
            if (pass.length() > 6) {
              return true;
            }
            return false;
          }


        });

        }
}
