package com.example.jp.reclwviewdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jp.reclwviewdemo.R;
import com.example.jp.reclwviewdemo.Sqlite_Database.Database;
import com.example.jp.reclwviewdemo.activity.Final_Order;
import com.example.jp.reclwviewdemo.model.DataModel;

import java.util.List;

public class Final_Oreder_Rcv extends RecyclerView.Adapter<Final_Oreder_Rcv.MyViewHolder> {
    Context context;
    List<DataModel>dataModels;
    int i=0;

    Database d;
    public Final_Oreder_Rcv(Context context, List<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;

        d=new Database(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.final_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.itemname.setText(dataModels.get(position).getItemName());
        holder.price.setText(dataModels.get(position).getItemPrice());
        holder.qun.setText(dataModels.get(position).getQuantity());
        holder.total.setText(dataModels.get(position).getTotal());


/*
            i +=Integer.parseInt(dataModels.get(position).getTotal());


        Toast.makeText(context, "total"+i, Toast.LENGTH_SHORT).show();
*/


            holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                d.deletdata(dataModels.get(position).getId());
                Intent in=new Intent(context,Final_Order.class);
                context.startActivity(in);
                Toast.makeText(context,"Data Deleted",Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView itemname,price,qun,total,amount;
        Button remove;



        public MyViewHolder(View itemView) {
            super(itemView);
            itemname=(TextView)itemView.findViewById(R.id.finalitem);
            price=(TextView)itemView.findViewById(R.id.finalprice);
            qun=(TextView)itemView.findViewById(R.id.finalqun);
            total=(TextView)itemView.findViewById(R.id.finaltotal);
            remove=(Button)itemView.findViewById(R.id.btnremove);
        }
    }
}
