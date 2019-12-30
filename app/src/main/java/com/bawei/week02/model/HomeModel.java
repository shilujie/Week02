package com.bawei.week02.model;
/*
 *@auther:史陆杰
 *@Date: 2019/12/30
 *@Time:14:30
 *@Description:${DESCRIPTION}
 **/


import com.bawei.week02.contract.IHomeContract;
import com.bawei.week02.model.bean.ShopBean;
import com.bawei.week02.util.NetUtil;
import com.google.gson.Gson;

public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getHomeData(String path, ModelCallBack modelCallBack) {
        String http = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?page=1&count=5&keyword=" + path;
        NetUtil.getInstance().getJsonGet(http, new NetUtil.MyCallBack() {
            @Override
            public void getJsonGet(String json) {
                ShopBean shopBean = new Gson().fromJson(json, ShopBean.class);
                modelCallBack.getSuccess(shopBean);
            }

            @Override
            public void getError(Throwable throwable) {
                modelCallBack.getFailer(throwable);
            }
        });
    }
}
