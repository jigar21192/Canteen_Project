package com.example.jp.reclwviewdemo.Login_Register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText roll,na,mo,pass;
    Button reg;
    String rollno,name,password;
    String mobile;
    TextView appCompatTextViewLoginLink;
    String regurl="https://zarnamak24.000webhostapp.com/register.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        roll=(EditText)findViewById(R.id.etRollno);
        na=(EditText)findViewById(R.id.etName);
        mo=(EditText)findViewById(R.id.etMobile);
        pass=(EditText)findViewById(R.id.etPassword);
        appCompatTextViewLoginLink=(TextView)findViewById(R.id.appCompatTextViewLoginLink);

        appCompatTextViewLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intentreg=new Intent(getApplicationContext(),Login_page.class);
                startActivity(Intentreg);

            }
        });
        reg=(Button)findViewById(R.id.btnSubmit);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rollno=roll.getText().toString();
                if(TextUtils.isEmpty(rollno)){
                    Toast.makeText(Register.this, "plz Enter Rollno!", Toast.LENGTH_SHORT).show();
                }
                name=na.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(Register.this, "plz Enter your name!", Toast.LENGTH_SHORT).show();
                }
                mobile=mo.getText().toString();
                if(TextUtils.isEmpty(mobile) ){
                    Toast.makeText(Register.this, "plz Enter Mobile number", Toast.LENGTH_SHORT).show();}

                password=pass.getText().toString();
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Enter Password more than 6 digit ", Toast.LENGTH_SHORT).show();
                }

                StringRequest request=new StringRequest(Request.Method.POST, regurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(Register.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                            Intent s=new Intent(Register.this,Login_page.class);
                            startActivity(s);


                        }else {
                            Toast.makeText(Register.this, "Some Problem", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, "Error"+error, Toast.LENGTH_SHORT).show();
                    }
                })

                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>param=new HashMap<>();

                        param.put("name",name);
                        param.put("rollno",rollno);
                        param.put("mo",mobile);
                        param.put("password",password);

                        Log.e("Data",">>>>>>>>>>>>>>"+name+rollno+mobile+password);

                        return param;
                    }
                };
                RequestQueue queue= Volley.newRequestQueue(Register.this);
                queue.add(request);


            }



        });
    }



}

