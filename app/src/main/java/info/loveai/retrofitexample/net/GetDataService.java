package info.loveai.retrofitexample.net;

import java.util.List;

import info.loveai.retrofitexample.model.RetroPhoto;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();
}
