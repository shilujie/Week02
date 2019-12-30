package com.bawei.week02.contract;
/*
 *@auther:史陆杰
 *@Date: 2019/12/30
 *@Time:14:01
 *@Description:${DESCRIPTION}
 **/


import com.bawei.week02.model.bean.ShopBean;

public interface IHomeContract {
    interface IView{
        void getSuccess(ShopBean shopBean);
        void getFailer(Throwable throwable);
    }

    interface IPresenter{
        void getHomeData(String path);
    }

    interface IModel{
        void getHomeData(String path,ModelCallBack modelCallBack);

        interface ModelCallBack{
            void getSuccess(ShopBean shopBean);
            void getFailer(Throwable throwable);
        }
    }

}
