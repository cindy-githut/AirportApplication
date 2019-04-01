package com.cindymb.airportapplication.di;

import android.app.Application;
import android.content.Context;
import android.content.ServiceConnection;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.cindymb.airportapplication.BuildConfig;
import com.cindymb.airportapplication.SSLHelpers;
import com.cindymb.airportapplication.model.NearbyAirportRequestModel;
import com.cindymb.airportapplication.services.ApiService;
import com.cindymb.airportapplication.utils.LoggingHelper;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return application;
    }

    @Singleton
    @Provides
    ApiService providesApiService(Retrofit aRetrofit) {
        return aRetrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    Retrofit provideNetwork(OkHttpClient aOkHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .callFactory(aOkHttpClient)
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient providesOkHttpClient() {
        SSLHelpers sslHelpers = new SSLHelpers();
        SSLSocketFactory sslSocketFactory = null;
        OkHttpClient client;

        try {
            sslSocketFactory = getSSLSocketFactory();
            if (sslSocketFactory == null) {
                sslSocketFactory = getTrustAllSocketFactory();
            }
        } catch (Exception e) {
            LoggingHelper.information(AppModule.class, e.getMessage());
        }
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);
        httpClient.sslSocketFactory(sslSocketFactory);
        if (BuildConfig.DEBUG) {
            httpClient.addNetworkInterceptor(new StethoInterceptor());
        }
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        return httpClient.build();
    }


    public static SSLSocketFactory getTrustAllSocketFactory() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            return null;
        }
    }
    private static SSLSocketFactory getSSLSocketFactory() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return sslSocketFactory;
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * Provides the Application Context
     *
     * @return a Context
     */
    @Provides
    Context providesApplicationContext() {
        return application;
    }

    /**
     * Provides a single instance of the LocalBroadcastManager
     *
     * @return LocalBroadcastManager
     */
    @Provides
    LocalBroadcastManager providesLocalBroadcastManager() {
        return LocalBroadcastManager.getInstance(application);
    }

    @Provides
    NearbyAirportRequestModel providesNearbyAirportRequestModel() {
        return new NearbyAirportRequestModel();
    }
}
