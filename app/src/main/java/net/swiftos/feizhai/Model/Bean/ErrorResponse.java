package net.swiftos.feizhai.Model.Bean;

/**
 * Created by ganyao on 2016/10/31.
 */

public class ErrorResponse implements IResponse{

    private ResponseType responseType;
    private Throwable throwable;

    public ErrorResponse(ResponseType responseType, Throwable throwable) {
        this.responseType = responseType;
        this.throwable = throwable;
    }

    @Override
    public ResponseType getResponseType() {
        return responseType;
    }

    public Throwable getThrowable(){
        return throwable;
    }
}