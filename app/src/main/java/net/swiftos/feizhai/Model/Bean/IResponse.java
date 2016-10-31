package net.swiftos.feizhai.Model.Bean;

import java.io.Serializable;

/**
 * Created by ganyao on 2016/10/27.
 */

public interface IResponse extends Serializable{

    public ResponseType getResponseType();

    enum ResponseType{
        Ads,
        Version,
        NumberList,
        Login
    }
}
