package kg.geektech.postapp;

import android.app.Application;

import kg.geektech.postapp.remote.PostApi;
import kg.geektech.postapp.remote.RetrofitClient;

public class App extends Application {
    private RetrofitClient retrofitClient;
    public static PostApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.createApi();
    }
}
