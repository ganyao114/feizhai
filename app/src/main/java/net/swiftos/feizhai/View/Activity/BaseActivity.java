package net.swiftos.feizhai.View.Activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import net.swiftos.eventposter.Impls.CustomEvent.Annotation.InjectEvent;
import net.swiftos.feizhai.Application.FeiZhaiApplication;
import net.swiftos.feizhai.Component.AppComponent;
import net.swiftos.feizhai.Model.Event.LoginedEvent;
import net.swiftos.feizhai.Presenter.Controller.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by ganyao on 2016/10/26.
 */
public abstract class BaseActivity extends AppCompatActivity{

    private BasePresenter basePresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(getContentLayout());
        ButterKnife.bind(this);
        basePresenter = setupActivityComponent(FeiZhaiApplication.getAppComponent());
        initView();
        if (basePresenter != null) {
            basePresenter.onViewInited();
        }
        initData();
    }

    @InjectEvent
    public void onEvent(LoginedEvent event) {
        Log.e("gy", "event");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (basePresenter != null) {
            basePresenter.onViewDestoryed();
        }
    }

    protected abstract @LayoutRes int getContentLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract BasePresenter setupActivityComponent(AppComponent appComponent);

}
