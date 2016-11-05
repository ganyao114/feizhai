package net.swiftos.feizhai.Component;

import android.content.Context;

import net.swiftos.feizhai.Model.Api.FeizhaiDaoApi;
import net.swiftos.feizhai.Model.Api.FeizhaiHttpApi;
import net.swiftos.feizhai.Model.Source.BaseModel;
import net.swiftos.feizhai.Module.AppModule;
import net.swiftos.feizhai.Module.FeizhaiServiceModule;
import net.swiftos.feizhai.Presenter.Controller.BasePresenter;
import net.swiftos.feizhai.View.Activity.BaseActivity;

import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by ganyao on 2016/10/31.
 */
@Singleton
@Component(modules = {AppModule.class, FeizhaiServiceModule.class})
public interface AppComponent {
    void inject(BaseActivity activity);

    void inject(BaseModel model);

    void inject(BasePresenter presenter);

    Context globalContext();

    ConcurrentHashMap globalData();

    FeizhaiHttpApi httpApi();

    FeizhaiDaoApi daoApi();

    OkHttpClient okHttpClient();
}
