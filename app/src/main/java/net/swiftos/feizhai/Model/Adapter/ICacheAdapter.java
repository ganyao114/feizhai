package net.swiftos.feizhai.Model.Adapter;


/**
 * 业务实体 -> Realm缓存实体 适配器
 * Created by ganyao on 2016/11/4.
 */

public interface ICacheAdapter<C,T> {
    C ModelToCache(T t);

}
