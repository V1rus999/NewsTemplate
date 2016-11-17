package com.droidit.newstemplate.dependencyInjection;

import com.droidit.domain.posts.PostService;
import com.droidit.retrofit.RetrofitPostService;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JohannesC on 01-Jun-16.
 */
@Module
public class NetworkModule {

    private final String baseUrl;

    public NetworkModule(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    public GsonConverterFactory provideGson() {
        return GsonConverterFactory.create();
    }

    @Provides
    public Retrofit provideApiClient(GsonConverterFactory gsonConverterFactory) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        //Add logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        client.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public PostService providePostService(RetrofitPostService retrofitPostService) {
        return retrofitPostService;
    }
}
