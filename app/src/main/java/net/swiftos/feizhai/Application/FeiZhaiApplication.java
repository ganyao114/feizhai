package net.swiftos.feizhai.Application;

import android.app.Application;
import android.content.Context;

import net.gy.SwiftFrameWork.Core.S;
import net.swiftos.eventposter.Core.EventPoster;
import net.swiftos.eventposter.Presenter.Presenter;
import net.swiftos.feizhai.Component.AppComponent;
import net.swiftos.feizhai.Component.DaggerAppComponent;
import net.swiftos.feizhai.Module.AppModule;
import net.swiftos.feizhai.Module.FeizhaiServiceModule;
import net.swiftos.feizhai.Presenter.Controller.MainPresenter;
import net.swiftos.feizhai.View.Activity.MainActivity;

/**
 * Created by gy939 on 2016/10/18.
 */

public class FeiZhaiApplication extends Application{

    private static AppComponent appComponent;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        S.init(this);
        EventPoster.init(this);
        context = this;
        EventPoster.PreLoadDeep(new Class[]{MainActivity.class, MainPresenter.class});
        createAppComp();
        Presenter.establish();
        Presenter.With(null).start(MainPresenter.class);
    }

    private void createAppComp(){
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .feizhaiServiceModule(new FeizhaiServiceModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static Context getContext(){
        return context;
    }

}
