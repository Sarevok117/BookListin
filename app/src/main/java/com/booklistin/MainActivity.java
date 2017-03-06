package com.booklistin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.booklistin.models.Page;
import com.booklistin.utils.ServiceSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<Page> call = ServiceSingleton.getmInstance().getService().getBookPage();
        call.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                if (response.code() == 200) {
                    Log.d("RETROFIT SUCCESS", "got Page");
                    Log.e("test", response.body().getModules().get(1).getBooks().get(0).getShortTitle());
                } else {
                    Log.d("RETROFIT SUCCESS", "could not get Page");
                }
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                Log.d("RETROFIT FAILURE", "could not get Page: " + t.getMessage());
            }
        });
    }
}
