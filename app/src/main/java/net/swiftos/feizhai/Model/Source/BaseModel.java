package net.swiftos.feizhai.Model.Source;


import net.swiftos.feizhai.Application.FeiZhaiApplication;
import net.swiftos.feizhai.Exception.BaseFeizhaiException;
import net.swiftos.feizhai.Exception.ErrorHandler;
import net.swiftos.feizhai.Exception.FeizhaiExceptionAdapter;
import net.swiftos.feizhai.Exception.FeizhaiHttpServiceException;
import net.swiftos.feizhai.Exception.FeizhaiNetworkException;
import net.swiftos.feizhai.Model.Api.FeizhaiHttpApi;
import net.swiftos.feizhai.Model.Bean.BaseResponse;
import net.swiftos.feizhai.Model.Bean.ErrorResponse;
import net.swiftos.feizhai.Model.Bean.IResponse;
import net.swiftos.feizhai.Utils.FeizhaiLog;
import net.swiftos.feizhai.Utils.NetworkUtil;


import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ganyao on 2016/10/26.
 */

public class BaseModel {

    private final static String TAG = "Model --->";

    @Inject
    protected FeizhaiHttpApi httpApi;

    public BaseModel() {
        FeiZhaiApplication.getAppComponent().inject(this);
    }

    /**
     * 返回值公共字段的处理，包括业务异常的抛出
     * @param <T>
     */
    public class BaseHttpFunc<T> implements Func1<BaseResponse<T>, T> {

        @Override
        public T call(BaseResponse<T> baseResponse) {
            int status = baseResponse.getStatus();
            String msg = baseResponse.getMessage();
            T data = baseResponse.getData();
            if (status != 1){
                FeizhaiLog.LOGE(TAG,"status = " + status + "msg = " + msg);
                throw new FeizhaiHttpServiceException(status, msg);
            }
            return data;
        }
    }

    public <T> Observable<T> getAsyncObservable(Observable<BaseResponse<T>> observable) {
        return observable
                .doOnSubscribe(this::checkNetwork)
                .map(new BaseHttpFunc<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new FeizhaiExceptionAdapter<T>())
                .doOnError(ErrorHandler.getInstance());
    }

    public <T> Observable<T> getAsyncObservableWithCache(IGetFromCache<T> getFromCache, ISaveToCache<T> saveToCache, Observable<BaseResponse<T>> observable) {
        Observable httpObservable = observable
                .doOnSubscribe(this::checkNetwork)
                .map(new BaseHttpFunc<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new FeizhaiExceptionAdapter<T>())
                .doOnNext( t -> saveToCache.toCache(t))
                .doOnError(ErrorHandler.getInstance());
        return Observable.defer(new Func0<Observable<T>>() {
            @Override
            public Observable<T> call() {
                T res = null;
                res = getFromCache.fromCache();
                if (res == null) {
                    return httpObservable;
                } else {
                    return Observable.just(res)
                            .observeOn(AndroidSchedulers.mainThread());
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .publish()
                .autoConnect();
    }

    protected void checkNetwork() throws BaseFeizhaiException {
        if (!NetworkUtil.checkNetWorkStatus(FeiZhaiApplication.getContext())) {
            throw new FeizhaiNetworkException("no network");
        }
    }


    public interface IGetFromCache<T> {
        T fromCache();
    }

    public interface ISaveToCache<T> {
        void toCache(T t);
    }

    public interface IBaseCallBack{
        void onSuccess(IResponse t);
        void onFailure(ErrorResponse error);
    }

}
