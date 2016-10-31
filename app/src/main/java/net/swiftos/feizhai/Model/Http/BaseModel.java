package net.swiftos.feizhai.Model.Http;


import net.swiftos.feizhai.Application.FeiZhaiApplication;
import net.swiftos.feizhai.Exception.FeizhaiHttpServiceException;
import net.swiftos.feizhai.Model.Api.FeizhaiHttpApi;
import net.swiftos.feizhai.Model.Bean.BaseResponse;
import net.swiftos.feizhai.Utils.FeizhaiLog;

import javax.inject.Inject;

import rx.functions.Func1;

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

}
