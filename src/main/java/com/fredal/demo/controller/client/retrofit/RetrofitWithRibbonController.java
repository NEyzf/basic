package com.fredal.demo.controller.client.retrofit;

import com.fredal.demo.bean.User;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/retrofit/ribbon")
public class RetrofitWithRibbonController {

    @Autowired
    OkHttpClient.Builder builder;
    String next = System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");
    String urlb = "http://" + next + ":80/";
    String urlp = System.getenv("ExternalAddr") == null ? "http://www.baidu.com/" : System.getenv("ExternalAddr");
    String urlb1 = "http://" + next + ":80/feign/self/";
    String urlb2 = "http://" + next + ":80/feign/self/getBpost/";

    Retrofit retrofitb, retrofitb1, retrofitp, retrofitgetb1, retrofitgetb2;
    ToBRet clientb, clientb1;
    PassThrough clientp;
    GetB1 getB1;
    GetB2 getB2;

    @PostConstruct
    public void init() {
        retrofitb = new Retrofit.Builder().baseUrl(urlb).client(builder.build()).addConverterFactory(ScalarsConverterFactory.create()).build();
        retrofitb1 = new Retrofit.Builder().baseUrl(urlb).client(builder.build()).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitgetb1 = new Retrofit.Builder().baseUrl(urlb1).client(builder.build()).addConverterFactory(ScalarsConverterFactory.create()).build();
        retrofitgetb2 = new Retrofit.Builder().baseUrl(urlb2).client(builder.build()).addConverterFactory(ScalarsConverterFactory.create()).build();
        retrofitp = new Retrofit.Builder().baseUrl(urlp).client(builder.build()).addConverterFactory(ScalarsConverterFactory.create()).build();
        clientb = retrofitb.create(ToBRet.class);
        clientb1 = retrofitb1.create(ToBRet.class);
        clientp = retrofitp.create(PassThrough.class);
        getB1 = retrofitgetb1.create(GetB1.class);
        getB2 = retrofitgetb2.create(GetB2.class);
    }

    @GetMapping(value = "/AtoB1")
    public String getB1() throws IOException {
        return getB1.getB1().execute().body();
    }

    @GetMapping(value = "/AtoB2")
    public String getB2() throws IOException {
        return getB2.getB2().execute().body();
    }

    @GetMapping(value = "/getBpost")
    public String getBpost() throws IOException {
        return clientb.post().execute().body();
    }

    @GetMapping(value = "/getBdelete")
    public String getBdelete() throws IOException {
        return clientb.delete().execute().body();
    }

    @GetMapping(value = "/getBhead")
    public Response<Void> getBhead() throws IOException {
        return clientb.head().execute();
    }

    @GetMapping(value = "/getBoptions")
    public String getBoptions() throws IOException {
        return clientb.options().execute().body();
    }

    @GetMapping(value = "/getBput")
    public String getBput() throws IOException {
        return clientb.put().execute().body();
    }

    @GetMapping(value = "/getBpatch")
    public String getBpatch() throws IOException {
        return clientb.patch().execute().body();
    }

    @GetMapping(value = "/getBheadertest")
    public String getBheadertest() throws IOException {
        return clientb.headertest().execute().body();
    }

    @GetMapping(value = "/getBrequestparamtest")
    public String getBrequestparamtest() throws IOException {
        return clientb.requestparamtest(1000, "amstest").execute().body();
    }

    @GetMapping(value = "/getBpathvariabletest")
    public String getBpathvariabletest() throws IOException {
        return clientb.pathvariabletest("amstest").execute().body();
    }

    @GetMapping(value = "/getBbodytest")
    public String getBbodytest() throws IOException {
        User user = new User("mse", "12345");
        return clientb1.getUser(user).execute().body().toString();
    }

    @GetMapping(value = "/getBUser")
    public String getBUser() throws IOException {
        User user = new User("mse", "12345");
        return clientb1.getUser(user).execute().body().toString();
    }

    @GetMapping(value = "/asynchronous/getBUser")
    public String AsynchronousgetBUser() {
        User user = new User("mse", "12345");
        Call<User> call = clientb1.getUser(user);
        final String[] res = new String[1];
        CountDownLatch countDownLatch = new CountDownLatch(1);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                res[0] = response.body().toString();
                System.out.println(res[0]);
                countDownLatch.countDown();
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return res[0];
    }

    public interface ToBRet {
        @POST("/post")
        Call<String> post();

        @GET("/get")
        Call<String> get();

        @DELETE("/delete")
        Call<String> delete();

        @HEAD("/head")
        Call<Void> head();

        @OPTIONS("/options")
        Call<String> options();

        @PUT("put")
        Call<String> put();

        @PATCH("patch")
        Call<String> patch();

        @GET("/headertest")
        @Headers({"req1:v1", "req2:v2", "req3:v3"})
        Call<String> headertest();

        @GET("/requestparamtest")
        Call<String> requestparamtest(@Query("id") int id, @Query("name") String name);

        @GET("/pathvariabletest/{name}")
        Call<String> pathvariabletest(@Path("name") String name);

        @POST("/getUser")
        @Headers({"req1:v1", "Accept:application/json", "Content-Type:application/json"})
        Call<User> getUser(@Body User user);
    }


    public interface PassThrough {
        @GET("/")
        Call<String> passthrough();
    }

    public interface GetB1 {
        @GET("getBpost")
        Call<String> getB1();
    }

    public interface GetB2 {
        @GET(".")
        Call<String> getB2();
    }
}
