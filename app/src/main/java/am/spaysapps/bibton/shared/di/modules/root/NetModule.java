package am.spaysapps.bibton.shared.di.modules.root;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import am.spaysapps.bibton.BuildConfig;
import am.spaysapps.bibton.shared.helpers.SharedPreferencesHelper;
import am.spaysapps.bibton.shared.utils.Constants;
import am.spaysapps.bibton.shared.utils.NetworkStatusUtils;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetModule {

    private final int mMaxStale = 60 * 60 * 24 * 5; // 5-days

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, Application application) {
        final SharedPreferencesHelper shared = new SharedPreferencesHelper(application);

        return new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            // Customize the request
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                  //  .header("Authorization", "Bearer " + shared.getStringSharedPreferences(Constants.TOKEN))
                    .header("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImY3ZmZmZDczMDBhOTNhMzA3ZGEzMzNkMmY4YTM2MThkZmIxOWZmZGM4MzQ3MzFkOGYwNjBiNzlhZmRlNmM2YmRlNDU4MjE2YzgyMWUxMTA5In0.eyJhdWQiOiI1IiwianRpIjoiZjdmZmZkNzMwMGE5M2EzMDdkYTMzM2QyZjhhMzYxOGRmYjE5ZmZkYzgzNDczMWQ4ZjA2MGI3OWFmZGU2YzZiZGU0NTgyMTZjODIxZTExMDkiLCJpYXQiOjE1NDk0NjM4MjgsIm5iZiI6MTU0OTQ2MzgyOCwiZXhwIjoxNTgwOTk5ODI4LCJzdWIiOiI5NSIsInNjb3BlcyI6W119.K9lZ6Cgoan3e2pDAK5l0hZj5h-wjq7AbYUkJo7-A2Mx3Vrr56kJ7Dz8UooDh6krd5gdwIaHfYn-E1CmZcT6C-Xamihhhuy2JSz1GQu_kJATR1v2vEeAy6xe4t22vH5nUvY6pT0N5nO1egSK8vRPH4pALIon3A50u1pcP6MIbfL9e7Dohoe7VlFGsigS6ykYj0yDE7M7zF60BereRDiPTtFDcloKHJ_TkeYUKUC0IvpTqNXRS-PsD_ywgj7dFI1IqtJA2xulphLvv_f8XeTUipyE5azXJyTyduvdKOW1Jg7o-lIcD7BeNwYVMwrcY4DA4Yl64RQg5gQ6NJQmFHKjsf08RuMnhmmN4qEkqMxviZOymjRgh-DhzNLiL2oGJ-ePM9DLhueenWE2sENTwTTA6bhYAX9V3hkisGoX3eYM0jm6dbatZQ-EltBPN0THlWF8S8j8GiqCTwSWHGnfxXV5KwYMi3W9BWPA7-YodSuWAKS2Bj999SZp6ICt8WM4iTjW_nJDz2hl2Qtb14jjtWLwnFLYpm_gpH9zievbHBPJYvGAgQkFXKkjcick0XV-9SMZum6ceQW3srtIqsn1jlZ6HhRoACWZaxCdaf-aLtBMM6KRz889hzly3fRp2CiE-NsD6w7gRBjSC8qCmtzUKlZGPF6roEZ9AtJWPMYXCvokQmBs")
                    .header("OsType", "1")
                    .header("Cache-Control", (NetworkStatusUtils.isNetworkAvailable(application)) ?
                            "public, max-age=" + mMaxStale : "public, max-stale=" + mMaxStale)
                    .build();
            okhttp3.Response response = chain.proceed(request);
            response.cacheResponse();
            // Customize or return the response
            return response;
        })
                .cache(cache)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}