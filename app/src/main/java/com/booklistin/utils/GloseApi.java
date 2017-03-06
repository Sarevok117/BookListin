package com.booklistin.utils;

import com.booklistin.models.Page;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Varkiil on 06/03/2017.
 */

public interface GloseApi {

    @GET("booklists/free-books?_version=20150601")
    Call<Page> getBookPage();
}
