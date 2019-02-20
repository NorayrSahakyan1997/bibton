package am.bibton.shared.di.components.root;

import android.content.Context;

import javax.inject.Singleton;

import am.bibton.shared.di.modules.root.AppModule;
import am.bibton.shared.di.modules.root.NetModule;
import am.bibton.shared.helpers.SharedPreferencesHelper;
import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface IAppComponent {
    Retrofit retrofit();

    OkHttpClient okHttpClient();

    Context context();

    SharedPreferencesHelper sharedPreferences();
}