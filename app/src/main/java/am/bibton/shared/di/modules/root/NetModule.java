package am.bibton.shared.di.modules.root;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import am.bibton.BuildConfig;
import am.bibton.shared.helpers.SharedPreferencesHelper;
import am.bibton.shared.utils.Constants;
import am.bibton.shared.utils.NetworkStatusUtils;
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
                    //.header("Authorization", "Bearer " + shared.getStringSharedPreferences(Constants.TOKEN))
                    .header("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjhkMmZjYmRkOThkMmQ1OTZjZjdiYTNlZGE1Y2Y5ODg1NDRiZGY5ZDJkZDU1ZDUxYzcwODk3NmJhMGMwNmE0MzA4MzJlMTMxY2U3NjI2MGY3In0.eyJhdWQiOiI3IiwianRpIjoiOGQyZmNiZGQ5OGQyZDU5NmNmN2JhM2VkYTVjZjk4ODU0NGJkZjlkMmRkNTVkNTFjNzA4OTc2YmEwYzA2YTQzMDgzMmUxMzFjZTc2MjYwZjciLCJpYXQiOjE1NTEwODEyNjQsIm5iZiI6MTU1MTA4MTI2NCwiZXhwIjoxNTgyNjE3MjY0LCJzdWIiOiI4MyIsInNjb3BlcyI6W119.KdwThcK-UZS5sZ-HueF_oB0pWu9Dl8WouK_-Fc_g1YGfuyySpoL2XVOBo4lRDdoRblxKSLGY33fwY1MZ0MNIcX_tPui7bUMsdqY1kTfVnXVseslvUZTEtB_znsYNDThA9L6awdhkoh3fCSVt9tk1Ejoe0WUmhFyxxyWXfUjyOvNU9DekOkXTf8kr0zhYaDuGcRFbe_-v3wG0L0WoSAcWUxZkpF7B0FrmyBHoyVVceeJF53BS5kZLPAEM_Yxt8tpbbIr6t_ISYbVQIUFGnedkC9wXhak1Fai3XEPwf-2IJg7OzflrzbrsDh520tslyvZvkiAdz_Wg9IMrdvtzseeQzT2NoCwthKf3il8LkBMsDst8by0Ic02eSGDQzlWL60SAr3iJ3VCR-EVEaoOWTgepBPfTcGWEt11gSld7LlL1d52EQ5wD3iGPqsynedBvSDs-FcGYX_8MjoFP_ge1uOZqdnRQBXKdPoMAX3r6qZcYbmmSzOi7-52rbGllviq5xF7ofpTfE_8V_S3deh2AXPOiVjKaOY6QX6o01X8NWcgPsWwc8AgjM993KYPHV7rnK1cy_0TAGr3V57SCRvpWSFGKpANHauiEPDh_Z0MTQm8EWnf72nsIve2mScTYg3e1WO48LYrz5ckISNHcAHanZfRvo26INkq_d446VJXePsR_78g")
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