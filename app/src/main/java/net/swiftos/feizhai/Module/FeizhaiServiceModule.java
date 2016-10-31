package net.swiftos.feizhai.Module;

import android.accounts.Account;

import net.swiftos.feizhai.Application.FeiZhaiApplication;
import net.swiftos.feizhai.Model.Api.FeizhaiDaoApi;
import net.swiftos.feizhai.Model.Api.FeizhaiHttpApi;
import net.swiftos.feizhai.Model.Api.ShuduDaoRealmImpl;
import net.swiftos.feizhai.Static.Static;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ganyao on 2016/10/31.
 */
@Module
public class FeizhaiServiceModule {

    public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse(Static.DEFAULT_URL);

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BasicParamsInterceptor())
                .connectTimeout(5 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(10 * 1000, TimeUnit.MILLISECONDS)
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    public HttpUrl provideBaseUrl() {
        return PRODUCTION_API_URL;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder().client(provideOkHttpClient())
                .baseUrl(provideBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public FeizhaiHttpApi provideHttpApi() {
        return provideRetrofit().create(FeizhaiHttpApi.class);
    }

    @Provides
    @Singleton
    public FeizhaiDaoApi provideDaoApi(){
        return new ShuduDaoRealmImpl(Realm.getInstance(new RealmConfiguration.Builder(FeiZhaiApplication.getContext())
                .name("myOtherRealm.realm")
                .build()));
    }
    /**
     * 公共参数拦截器
     */
    public static class BasicParamsInterceptor implements Interceptor {

        public BasicParamsInterceptor() {

        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();
            HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
                    .addQueryParameter("appkey", "")
                    .addQueryParameter("sign", "")
                    .addQueryParameter("timestamp", "");

            Request newRequest = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
                    .url(authorizedUrlBuilder.build())
                    .header("User-Agent","")
                    .build();

            return chain.proceed(newRequest);
        }
    }
}
