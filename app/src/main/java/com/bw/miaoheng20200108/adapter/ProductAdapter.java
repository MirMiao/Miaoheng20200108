package com.bw.miaoheng20200108.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.miaoheng20200108.R;
import com.bw.miaoheng20200108.entity.CartEntity;

import java.util.List;

/**
 * 时间 :2020/1/8  8:56
 * 作者 :苗恒
 * 功能 :
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context context;
    List<CartEntity.ResultBean.ShoppingCartListBean> result;

    public ProductAdapter(Context context, List<CartEntity.ResultBean.ShoppingCartListBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(View.inflate(context, R.layout.item2, null));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.tv_name.setText(result.get(position).getCommodityName());
       holder.tv_price.setText(result.get(position).getPrice()+"");
       Glide.with(context).load(result.get(position).getPic()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        private final TextView tv_name;
        private final TextView tv_price;
        private final ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            imageView = itemView.findViewById(R.id.iv);
        }
    }

}
