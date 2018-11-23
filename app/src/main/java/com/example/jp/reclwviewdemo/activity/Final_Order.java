package com.example.jp.reclwviewdemo.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
import com.example.jp.reclwviewdemo.Sqlite_Database.Database;
import com.example.jp.reclwviewdemo.adapter.Final_Oreder_Rcv;
import com.example.jp.reclwviewdemo.adapter.RecycleViewAdepter;
import com.example.jp.reclwviewdemo.model.DataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Final_Order extends AppCompatActivity {
    RecyclerView rcv;
    Database d;
    String item,itemprice,quantity;
    TextView total;
   Button placeorder;
    int mtotal=0;
    String url="https://zarnamak24.000webhostapp.com/order_details.php";


    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String KEY_USERNAME = "username";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final__order);
        placeorder=(Button)findViewById(R.id.placeorder);
        total=(TextView)findViewById(R.id.amount);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final String user=sharedPreferences.getString("username","");

        rcv=(RecyclerView)findViewById(R.id.rc);

        d=new Database(Final_Order.this);

        List<DataModel> data_models=d.getAllDatabase();
        final ArrayList<String> selecteditem=new ArrayList<>();
       // selecteditem.add(item);
        final List<Integer> selecteditemprice=new ArrayList<>();
        final List<Integer> seletedqun=new ArrayList<>();


        for (int i=0;i<data_models.size();i++){
            mtotal+=Integer.parseInt(data_models.get(i).getTotal());
             selecteditem.add(String.valueOf(data_models.get(i).getItemName()));
             selecteditemprice.add(Integer.parseInt(data_models.get(i).getItemPrice()));
             seletedqun.add(Integer.parseInt(data_models.get(i).getQuantity()));

        }
        total.setText(String.valueOf(mtotal));

        Final_Oreder_Rcv adepter=new Final_Oreder_Rcv(this,data_models);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adepter);

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
                Random rnd = new Random();
                final StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) + "");
               // for (int i = 0; i < 5; i++)
               //     sb.append(chars[rnd.nextInt(chars.length)]);
                  //  sb.append(chars[rnd.nextInt(chars.length)]);
                     //rand.setText(sb);

                final Dialog dialog = new Dialog(Final_Order.this);
                dialog.setContentView(R.layout.popup_final);
                dialog.setTitle("Order Confirmation");
                final TextView ref = (TextView) dialog.findViewById(R.id.refCode);
                TextView payment = (TextView) dialog.findViewById(R.id.payment);
                        ref.setText(sb);
                        payment.setText(String.valueOf(mtotal));

                StringRequest req=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Final_Order.this, (CharSequence) error, Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                       Map<String,String>params=new HashMap<>();
                        params.put("rollno",user);
                        params.put("item_name", String.valueOf(selecteditem));
                        params.put("price", String.valueOf(selecteditemprice));
                        params.put("qty",String.valueOf(seletedqun));
                        params.put("ref_code", ref.getText().toString());
                        params.put("bill_total",total.getText().toString());

                        return params;
                    }
                }; dialog.show();

                RequestQueue q= Volley.newRequestQueue(Final_Order.this);
                q.add(req);

                d.deletealldata();
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed(); // this can go before or after your stuff below
        // do your stuff when the back button is pressed
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    }
