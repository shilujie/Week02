package com.bawei.week02.presenter;
/*
 *@auther:史陆杰
 *@Date: 2019/12/30
 *@Time:14:32
 *@Description:${DESCRIPTION}
 **/


import com.bawei.week02.base.BasePresenter;
import com.bawei.week02.contract.IHomeContract;
import com.bawei.week02.model.HomeModel;
import com.bawei.week02.model.bean.ShopBean;

public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {
    private HomeModel homeModel;
    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData(String path) {
        homeModel.getHomeData(path, new IHomeContract.IModel.ModelCallBack() {
            @Override
            public void getSuccess(ShopBean shopBean) {
                view.getSuccess(shopBean);
            }

            @Override
            public void getFailer(Throwable throwable) {
                view.getFailer(throwable);
            }
        });
    }
}
