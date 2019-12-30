package com.bawei.week02.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week02.R;
import com.bawei.week02.base.BaseActivity;
import com.bawei.week02.base.BasePresenter;
import com.bawei.week02.contract.IHomeContract;
import com.bawei.week02.model.bean.ShopBean;
import com.bawei.week02.presenter.HomePresenter;
import com.bawei.week02.view.adapter.MyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<HomePresenter> implements IHomeContract.IView {
    @BindView(R.id.rlv)
    RecyclerView rlv;

    @Override
    protected void initData() {
        mPresenter.getHomeData("手机");
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rlv.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected HomePresenter providerPresenter() {
        return new HomePresenter();
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void getSuccess(ShopBean shopBean) {
        List<ShopBean.ResultBean> result = shopBean.getResult();
        MyAdapter myAdapter = new MyAdapter(result);
        myAdapter.setOnClickListener(new MyAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position) {
               startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
        rlv.setAdapter(myAdapter);
    }

    @Override
    public void getFailer(Throwable throwable) {

    }
}
