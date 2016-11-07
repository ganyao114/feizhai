package net.swiftos.feizhai.Exception;


import rx.functions.Action1;

/**
 * Created by ganyao on 2016/11/4.
 */

public class ErrorHandler implements Action1<Throwable> {

    private static ErrorHandler handler = new ErrorHandler();

    public static ErrorHandler getInstance() {
        return handler;
    }

    @Override
    public void call(Throwable throwable) {

    }
}
