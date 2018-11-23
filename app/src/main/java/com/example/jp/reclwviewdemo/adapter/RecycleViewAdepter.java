package com.example.jp.reclwviewdemo.adapter;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.jp.reclwviewdemo.R;
import com.example.jp.reclwviewdemo.Sqlite_Database.Database;
import com.example.jp.reclwviewdemo.model.DataModel;
import com.example.jp.reclwviewdemo.model.Food;
import java.util.List;


public class RecycleViewAdepter extends RecyclerView.Adapter<RecycleViewAdepter.MyViewHolder> {
    private Context mContext;
    private List<Food> mdata;

    Integer cal_quantity;
    RequestOptions options;

    Database db;

    public RecycleViewAdepter(Context mContext,List<Food> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
        db=new Database(mContext);

        options=new RequestOptions().centerCrop().placeholder(R.drawable.loaading_shape).error(R.drawable.loaading_shape);

    }



    @NonNull
    @Override
    public RecycleViewAdepter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(mContext);
        view=inflater.inflate(R.layout.food_details2,parent,false);

        return new  MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {


        holder.tv_name.setText(mdata.get(position).getName());
        holder.price.setText(mdata.get(position).getPrice());

        Glide.with(mContext)
                .load(mdata.get(position)
                        .getImurl())
                .into(holder.imurl);
        final Integer getP= Integer.valueOf(holder.price.getText().toString());

        holder.cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DataModel dm=new DataModel();
                dm.setItemName(holder.tv_name.getText().toString());
                dm.setItemPrice(holder.price.getText().toString());
                dm.setQuantity(holder.qun.getText().toString());
                dm.setTotal(holder.tv_total.getText().toString());



                db.insertdata(dm);



                /*Toast.makeText(mContext, "Selected Item"+holder.tv_name.getText().toString()
                        +holder.price.getText().toString()
                        +holder.tv_total.getText().toString()
                        +holder.qun.getText().toString(), Toast.LENGTH_LONG).show();
*/
            }
        });




       holder. minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal_quantity=(Integer.parseInt((holder.qun.getText().toString())));
                if(cal_quantity!=1){
                    cal_quantity=cal_quantity-1;
                }
                holder.qun.setText(cal_quantity+"");
                Integer get_total=cal_quantity*getP;
                holder.tv_total.setText(get_total+"");
            }
        });


        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal_quantity= (Integer.parseInt(holder.qun.getText().toString()));
                cal_quantity=cal_quantity+1;
                holder.qun.setText(cal_quantity+"");
                Integer get_total=cal_quantity*getP;
                holder.tv_total.setText(get_total+"");
            }
        });


    }

    @Override
    public int getItemCount() {

        return mdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        ImageView imurl;
        TextView price;
        Button minus,plus;
        TextView qun,tv_total;
        Button cart_button;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            imurl=itemView.findViewById(R.id.imageView);
            minus=itemView.findViewById(R.id.decrement_button);
            qun=itemView.findViewById(R.id.txtCount);
            tv_total=itemView.findViewById(R.id.cost_text_view);
            plus=itemView.findViewById(R.id.increment_button);
            cart_button=itemView.findViewById(R.id.cart_button);
            }
    }
}
