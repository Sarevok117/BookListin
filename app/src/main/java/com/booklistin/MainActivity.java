package com.booklistin;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.booklistin.adapters.BookAdapater;
import com.booklistin.models.Page;
import com.booklistin.utils.ServiceSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.recyclerview.R.attr.layoutManager;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter bookAdapater;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = ProgressDialog.show(this, "Please wait", "Loading book list");

        recyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        Call<Page> call = ServiceSingleton.getmInstance().getService().getBookPage();
        call.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                if (response.code() == 200) {
                    Log.d("RETROFIT SUCCESS", "got Page");
                    if (response.body() != null && response.body().getModules() != null) {
                        for (int i = 0; i < response.body().getModules().size(); i++) {
                            if (response.body().getModules().get(i).getBooks() != null) {
                                bookAdapater = new BookAdapater(getApplicationContext(),
                                        response.body().getModules().get(i).getBooks());
                                recyclerView.setAdapter(bookAdapater);
                                recyclerView.setLayoutManager(layoutManager);

                                Toast.makeText(getApplicationContext(), "Book list loaded.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Log.d("RETROFIT SUCCESS", "body / modules is null");
                        Toast.makeText(getApplicationContext(), "Error while loading books.",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RETROFIT SUCCESS", "could not get Page");
                    Toast.makeText(getApplicationContext(), "Error while loading books.",
                            Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                Log.d("RETROFIT FAILURE", "could not get Page: " + t.getMessage());
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error while loading books.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
