package net.swiftos.feizhai.Presenter.Controller;


import net.swiftos.eventposter.Presenter.Presenter;
import net.swiftos.feizhai.Model.Source.BaseModel;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ganyao on 2016/10/26.
 */

public abstract class BasePresenter extends Presenter implements BaseModel.IBaseCallBack {

    protected CompositeSubscription mCompositeSubscription;

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    //RXjava注册
    public void addSubscription(Subscription subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscriber);
    }

    public void onViewInited(){

    }

    public void onViewDestoryed(){
        onUnsubscribe();
    }



}
