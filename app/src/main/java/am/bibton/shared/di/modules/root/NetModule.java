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
                    .header("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjA1MTMzNzBlZjAxYTE3NjdkZjdhNmRkMDQ1M2U1NWU1ZWI2OTE0NWU4MDI0YzJiNmZhMWUwNTlhYjdjYjk0ZmM5YjAzZTAyZmY2N2FiZjg5In0.eyJhdWQiOiI3IiwianRpIjoiMDUxMzM3MGVmMDFhMTc2N2RmN2E2ZGQwNDUzZTU1ZTVlYjY5MTQ1ZTgwMjRjMmI2ZmExZTA1OWFiN2NiOTRmYzliMDNlMDJmZjY3YWJmODkiLCJpYXQiOjE1NTE4NTYzNDgsIm5iZiI6MTU1MTg1NjM0OCwiZXhwIjoxNTgzNDc4NzQ4LCJzdWIiOiIxMDEiLCJzY29wZXMiOltdfQ.ewbr9JJ90vD5HlLFcH5iC4Zl0GYdaYAWWyzLKtrXCEXGcBmt6Woun2C5PrIkKxD7hIhS5yAIg9n3wK2iS5zmPwHx1Wvq3hyghjqRTam1dkvH336Ft160UIay7_Izjbp3jPTcQu4K2m7wI8McuBnmn5Vm45jzhUtY0y74E3pR1f2QAFvJVwFeXvzTw7hlawPL4e7VATIRIQkRdVVatTpVGf8VECrmDkatJ87mIvyQyKVpwrYEjt8cd8di7oRjMoblm2S9OMjg7-Vu5XsSNLAQUJ8Ys6l9XPt9qstA38PGYHQ1NxXt2Ec_NptNw2egkGthcuTUt1ki7BjGWQN5hQni_bBTngnrm46DoPnYlk8iEF6QiUpEPArzlcWAmKOQd5G1w82sdBVa88CFPMe7qFhq9t_PzXaSpuRY1QIx_zkZ07HgUjqwAgMtxRmtDt30slJySlVdrbzdqpnLE6dUm_Hxx4kGjqadgpI6x_l-SauxG8rsdIO5zDSyRhId-_PzbSOib1ObMuASVJjE7HW4FyYU7G8rcDoz3-AiJZXdUD38XKhYm6A2Wd3W1JZ_n_F8Y3pIEQiwVPxKHOxzt7Xh-DWaidNUBIRSdrSXn8cC5CPp2l31vWn8irSdN-YKCq2HN3pcv2R76uq_46WdmmCpMSFyoXShLURTw4fioYd6uxXfyWc")
                    .header("UDID", "123")
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