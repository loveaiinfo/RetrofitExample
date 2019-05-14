package info.loveai.retrofitexample.api;

import info.loveai.retrofitexample.model.UserModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubUsersApi {
    @GET("/users/{user}")
    Call<UserModel> repo(@Path("user") String user);
}
