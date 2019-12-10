package com.example.libgen;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @POST("json.php?")
    Call<JsonElement> information(@Query("ids") String ids, @Query("fields") String fields);

    @POST("json.php?fields=ID&mode=last")
    Call<JsonElement> sortdate(
            @Query("timefirst") String timefirst,
            @Query("timelast") String timelast);

    @POST("json.php?fields=Author,Title,Pages,Year,Publisher,Filesize,Extension,Locator,Coverurl,pagesinfile,descr,MD5")
    Call<JsonElement> databook(
            @Query("ids") String ids
    );

   // @GET("")
  //  Call<Image> getImage()
   // @POST("")
}
