package net.swiftos.feizhai.Exception;

/**
 * Created by ganyao on 2016/10/27.
 */

public class FeizhaiHttpServiceException extends BaseFeizhaiException {

    private int code;
    private String msg;

    public FeizhaiHttpServiceException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
