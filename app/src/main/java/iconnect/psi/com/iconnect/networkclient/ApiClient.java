package iconnect.psi.com.iconnect.networkclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static String URL_CI = "http://xsinfoways.net/savera/Api/";

    public static Retrofit getClientCI()
    {
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.addInterceptor(interceptor);
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//        retrofit = new Retrofit.Builder()
//                .baseUrl(URL_CI)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(client)
//                .build();
//
//        return retrofit;
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(300, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder().setLenient().serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_CI)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return retrofit;

    }

//    public static Retrofit getBillingClient(){
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.addInterceptor(interceptor);
//        retrofit = new Retrofit.Builder()
//                .baseUrl(URL_BILLING)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//        return retrofit;
//    }
//
//    Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();
//    public static Retrofit getClient() {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.addInterceptor(interceptor);
//        retrofit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(client)
//                .build();
//
//        return retrofit;
//
//
//      /*  OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//
//                // Request customization: add request headers
//                Request.Builder requestBuilder = original.newBuilder()
//                        .header("Content-Type", "application/x-www-form-urlencoded"); // <-- this is the important line
//
//                Request request = requestBuilder.build();
//                return chain.proceed(request);
//            }
//        });
//
//        OkHttpClient client = httpClient.build();
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//        return retrofit;*/
//    }
//}

}
