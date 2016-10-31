package net.swiftos.feizhai.Module;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ganyao on 2016/10/31.
 */
@Module
public class AppModule {

    private Application globalApp;

    public AppModule(Application globalApp) {
        this.globalApp = globalApp;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return globalApp;
    }

    @Provides
    @Singleton
    public ConcurrentHashMap provideGlobalData() {
        return new ConcurrentHashMap<>();
    }
}