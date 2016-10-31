package net.swiftos.feizhai.Model.Api;


import io.realm.Realm;

/**
 * Created by ganyao on 2016/10/27.
 */

public class ShuduDaoRealmImpl implements FeizhaiDaoApi{

    private Realm realm;

    public ShuduDaoRealmImpl(Realm realm) {
        this.realm = realm;
    }


}
