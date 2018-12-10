package com.app.cellstudio.data.http;

import com.app.cellstudio.data.api.ApiService;
import com.app.cellstudio.data.environment.Environment;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.internal.platform.Platform.INFO;

/**
 * Created by coyan on 29/11/2018.
 */

public class BaseHttpClient implements HttpClient {

    private final Environment environment;

    private OkHttpClient okHttpClient;

    private ApiService apiService;

    public BaseHttpClient(Environment environment) {
        this.environment = environment;

        okHttpClient = createOkHttpClient();
        createService(okHttpClient);
    }

    @Override
    public ApiService getApiService() {
        return apiService;
    }

    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> {
            Platform.get().log(INFO, message, null);
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
        return client;
    }

    private void createService(OkHttpClient okHttpClient) {
        apiService = createApiService(okHttpClient);
    }

    private ApiService createApiService(OkHttpClient client) {
        String baseUrl = environment.getBaseUrl();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(ApiService.class);
    }
}
