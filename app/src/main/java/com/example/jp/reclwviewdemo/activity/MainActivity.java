package com.example.jp.reclwviewdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jp.reclwviewdemo.Login_Register.Login_page;
import com.example.jp.reclwviewdemo.R;
import com.example.jp.reclwviewdemo.adapter.RecycleViewAdepter;
import com.example.jp.reclwviewdemo.model.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String  JSON_URL="http://zarnamak24.000webhostapp.com/get_data.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Food> lstfood;
    private RecyclerView recyclerView;
    private NavigationView navigationView;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private DrawerLayout mDraw;



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDraw.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycle_view);
        Button conf=findViewById(R.id.confirm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDraw = (DrawerLayout) findViewById(R.id.main1);
        navigationView = (NavigationView) findViewById(R.id.navView);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        nav();
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();



        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Final_Order.class);

                startActivity(intent);
            }
        });

        jsonrequest();
        lstfood=new ArrayList<>();


    }


    public void nav() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.chooseItem:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.wishlist:
                        Intent i = new Intent(MainActivity.this, Final_Order.class);
                        startActivity(i);
                        break;
                    case R.id.logout:
                        editor.clear();
                        editor.commit();
                        Intent logout=new Intent(MainActivity.this, Login_page.class);
                        startActivity(logout);

                        break;
                 /*   case R.id.itemcode:
                        Intent intent2=new Intent(Main.this,ItemCode.class);
                        startActivity(intent2);*/
                }
                item.setChecked(true);
                mDraw.closeDrawers();
                return true;
            }
        });
    }



    private void jsonrequest() {

        request=new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject=null;
                for (int i=0;i<response.length();i++){
                    try {
                        jsonObject=response.getJSONObject(i);
                        Food food=new Food();
                        food.setName(jsonObject.getString("item_name"));
                        food.setPrice(jsonObject.getString("price"));
                        food.setImurl(jsonObject.getString("image"));
                        lstfood.add(food);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setuprecycleview(lstfood);
            }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }


    private void setuprecycleview(List<Food> lstfood) {
        RecycleViewAdepter adepter=new RecycleViewAdepter(this,lstfood);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adepter);
        recyclerView.setHasFixedSize(true);


    }


}
