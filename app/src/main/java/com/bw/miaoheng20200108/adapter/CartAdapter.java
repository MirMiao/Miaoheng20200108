package com.bw.miaoheng20200108.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.miaoheng20200108.MainActivity;
import com.bw.miaoheng20200108.R;
import com.bw.miaoheng20200108.entity.CartEntity;

import java.util.List;

/**
 * 时间 :2020/1/8  8:56
 * 作者 :苗恒
 * 功能 :
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    List<CartEntity.ResultBean> result;

    public CartAdapter(Context context, List<CartEntity.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(View.inflate(context, R.layout.item, null));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
           holder.textView.setText(result.get(position).getCategoryName());
        ProductAdapter productAdapter = new ProductAdapter(context, result.get(position).getShoppingCartList());
        holder.rv_product.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_product.setAdapter(productAdapter);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final RecyclerView rv_product;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
            rv_product = itemView.findViewById(R.id.rv_product);
        }
    }

}
