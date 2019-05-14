package info.loveai.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import info.loveai.retrofitexample.api.GithubUsersApi;
import info.loveai.retrofitexample.model.Translation1;
import info.loveai.retrofitexample.model.UserModel;
import info.loveai.retrofitexample.net.TranslationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// https://www.vogella.com/tutorials/Retrofit/article.html
// https://www.dev2qa.com/retrofit-android-get-post-example/
// post example:https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Retrofit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testGithubGet();
        translationRequest();
    }

    public void testGithubGet(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubUsersApi service = retrofit.create(GithubUsersApi.class);
        Call<UserModel> model = service.repo("Guolei1130");

        model.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                Log.d(TAG, response.body().getLogin());

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });
    }

    public void translationRequest() {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        TranslationService request = retrofit.create(TranslationService.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation1> call = request.getCall("I love you");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation1>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                // 步骤7：处理返回的数据结果：输出翻译的内容
                Log.d(TAG,response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                Log.e(TAG,"请求失败");
                Log.e(TAG,throwable.getMessage());
            }
        });
    }

}
