package net.swiftos.feizhai.Exception;

/**
 * Created by ganyao on 2016/11/4.
 */

public class ExceptionEngine {

    public static BaseFeizhaiException handlerException(Throwable throwable){

        return new BaseFeizhaiException(throwable.getMessage());
    }

    public class ErrorCode {
//        public final static int
    }
}
