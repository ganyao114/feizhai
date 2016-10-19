package net.swiftos.feizhai.Application;

import android.app.Application;

import net.gy.SwiftFrameWork.Core.S;
import net.swiftos.eventposter.Core.EventPoster;
import net.swiftos.eventposter.Presenter.Presenter;
import net.swiftos.feizhai.Presenter.Controller.MainPresenter;
import net.swiftos.feizhai.View.Activity.MainActivity;

/**
 * Created by gy939 on 2016/10/18.
 */

public class FeiZhaiApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        S.init(this);
        EventPoster.init(this);
        EventPoster.PreLoad(new Class[]{MainActivity.class, MainPresenter.class});
        Presenter.establish();
        Presenter.With(null).start(MainPresenter.class);
    }
}
