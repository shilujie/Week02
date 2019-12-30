package com.bawei.week02.view.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.week02.R;
import com.bawei.week02.base.BaseActivity;
import com.bawei.week02.model.bean.MyBean;
import com.bawei.week02.presenter.HomePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity<HomePresenter> {
    @BindView(R.id.ed_text)
    EditText edText;
    @BindView(R.id.but)
    Button but;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.chuan1)
    Button chuan1;
    @BindView(R.id.chuan2)
    Button chuan2;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                CodeUtils.analyzeByImageView(img, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(Main2Activity.this, "" + result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(Main2Activity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected HomePresenter providerPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main2;
    }
    @OnClick({R.id.but,R.id.chuan1, R.id.chuan2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but:
                String s = edText.getText().toString();
                Bitmap image = CodeUtils.createImage(s, 400, 400, null);
                img.setImageBitmap(image);
                break;
            case R.id.chuan1:
                EventBus.getDefault().post("字符串");
                break;
            case R.id.chuan2:
                EventBus.getDefault().post(new MyBean("张三","20"));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventF(MyBean myBean){
        Toast.makeText(this, ""+myBean, Toast.LENGTH_SHORT).show();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventS(String s){
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
