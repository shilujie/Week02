package com.bawei.week02.view.adapter;
/*
 *@auther:史陆杰
 *@Date: 2019/12/30
 *@Time:14:39
 *@Description:${DESCRIPTION}
 **/


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week02.R;
import com.bawei.week02.model.bean.ShopBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHoulder> {

    private List<ShopBean.ResultBean> result;

    public MyAdapter(List<ShopBean.ResultBean> result) {

        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHoulder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(parent.getContext(), R.layout.layout_item, null);
        return new MyViewHoulder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoulder holder, int position) {
        Glide.with(holder.img)
                .load(result.get(position).getMasterPic())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.img);
        holder.name.setText(result.get(position).getCommodityName());
        holder.price.setText("￥"+result.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHoulder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;

        public MyViewHoulder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onItemClick(int position);
    }
}
