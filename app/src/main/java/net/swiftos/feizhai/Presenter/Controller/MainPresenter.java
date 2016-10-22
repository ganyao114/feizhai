package net.swiftos.feizhai.Presenter.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import net.swiftos.eventposter.Core.EventPoster;
import net.swiftos.eventposter.Impls.ActivityLife.Annotation.ActivityLife;
import net.swiftos.eventposter.Impls.ActivityLife.Entity.ActivityLifeType;
import net.swiftos.eventposter.Impls.CustomEvent.Handler.CustomEventHandler;
import net.swiftos.eventposter.Impls.ViewEvent.Annotation.OnClick;
import net.swiftos.eventposter.Impls.ViewEvent.Handler.ViewEventHandler;
import net.swiftos.eventposter.Impls.ViewEvent.Interface.OnViewAttachListener;
import net.swiftos.eventposter.Presenter.IPresenter;
import net.swiftos.eventposter.Presenter.Presenter;
import net.swiftos.feizhai.Protocol.MainProtocol;
import net.swiftos.feizhai.R;
import net.swiftos.feizhai.View.Activity.MainActivity;

import static net.swiftos.feizhai.Protocol.MainProtocol.MainContext;

/**
 * Created by gy939 on 2016/10/18.
 */

public class MainPresenter extends Presenter implements OnViewAttachListener,MainProtocol.Presenter{

    private MainProtocol.View mainView;

    @Override
    public void onPresenterDestory(IPresenter context) {
        super.onPresenterDestory(context);
        EventPoster.With(ViewEventHandler.class).removeViewAttachListener(MainContext,this);
    }

    @Override
    public void onPresenterInit(IPresenter context) {
        super.onPresenterInit(context);
        EventPoster.With(ViewEventHandler.class).addViewAttachListener(MainContext,this);
    }

    @ActivityLife(lifeType = ActivityLifeType.OnCreate,activity = MainActivity.class)
    public void OnMainViewCreated(Activity activity,Bundle savedInstanceState){
        mainView = (MainProtocol.View) activity;
    }

    @ActivityLife(lifeType = ActivityLifeType.OnDestory,activity = MainActivity.class)
    public void OnMainViewDestoryed(Activity activity){
        mainView = null;
    }

    @OnClick(context = MainContext,viewIds = R.id.button)
    public void OnClick(View view){
        mainView.setText("你好");
        EventPoster.With(CustomEventHandler.class).As("你好").BroadCast();
    }

    @Override
    public void onViewAttached(String context, View view) {

    }

    @Override
    public void onViewDettached(String context, View view) {

    }
}
