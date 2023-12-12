package com.fredal.demo.controller.client.okhttp;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.ExceptionUtil;
import com.fredal.demo.bean.User;
import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/okhttp")
public class OkhttpController {
    OkHttpClient client = new OkHttpClient();
    String next = System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");
    String urlb = "http://" + next + ":80";

    @RequestMapping(value = "/passthrough", method = RequestMethod.GET)
    public String passthrough() {
        String urlp = System.getenv("ExternalAddr") == null ? "http://www.baidu.com" : System.getenv("ExternalAddr");
        Request request = new Request.Builder().url(urlp).get().build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return ExceptionUtil.getStackTrace(e);
        }
    }

    @RequestMapping(value = "/getBpost", method = RequestMethod.GET)
    public String getBpost() {
        String next_hop = urlb + "/post";
        RequestBody body = new FormBody.Builder().add("username", "test").build();
        Request request = new Request.Builder().url(next_hop).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return ExceptionUtil.getStackTrace(e);
        }
    }

    @RequestMapping(value = "/getBdelete", method = RequestMethod.GET)
    public String getBdelete() {
        String next_hop = urlb + "/delete";
        Request request = new Request.Builder().url(next_hop).delete().build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return ExceptionUtil.getStackTrace(e);
        }
    }

    @RequestMapping(value = "/getBhead", method = RequestMethod.GET)
    public String getBhead() {
        String next_hop = urlb + "/head";
        Request request = new Request.Builder().url(next_hop).head().build();
        try (Response response = client.newCall(request).execute()) {
            return "return code: " + response.code();
        } catch (IOException e) {
            e.printStackTrace();
            return ExceptionUtil.getStackTrace(e);
        }
    }

    @RequestMapping(value = "/getBput", method = RequestMethod.GET)
    public String getBput() {
        String next_hop = urlb + "/put";
        RequestBody body = new FormBody.Builder().add("username", "test").build();
        Request request = new Request.Builder().url(next_hop).put(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return ExceptionUtil.getStackTrace(e);
        }
    }

    @RequestMapping(value = "/getBpatch", method = RequestMethod.GET)
    public String getBtrace() {
        String next_hop = urlb + "/patch";
        RequestBody body = new FormBody.Builder().add("username", "test").build();
        Request request = new Request.Builder().url(next_hop).patch(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return ExceptionUtil.getStackTrace(e);
        }
    }

    @RequestMapping(value = "/getBheadertest", method = RequestMethod.GET)
    public String getBheadertest() {
        String next_hop = urlb + "/headertest";
        Request request = new Request.Builder().
                header("req1", "v1").
                header("req2", "v2").
                header("req3", "v3").
                url(next_hop).get().build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return ExceptionUtil.getStackTrace(e);
        }
    }

    @RequestMapping(value = "/getBrequestparamtest", method = RequestMethod.GET)
    public String getBrequestparamtest() {
        String next_hop = urlb + "/requestparamtest";
        HttpUrl httpUrl = HttpUrl.parse(next_hop).newBuilder().
                addQueryParameter("id", "1000").
                addQueryParameter("name", "amstest").build();
        Request request = new Request.Builder().url(httpUrl.url().toString()).get().build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return ExceptionUtil.getStackTrace(e);
        }
    }

    @RequestMapping(value = "/getBpathvariabletest", method = RequestMethod.GET)
    public String getBpathvariabletest() {
        String next_hop = urlb + "/pathvariabletest/amstest";
        Request request = new Request.Builder().url(next_hop).get().build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return ExceptionUtil.getStackTrace(e);
        }
    }

    @RequestMapping(value = "/getBbodytest", method = RequestMethod.GET)
    public String getBbodytest() throws JSONException {
        User user = new User();
        user.name = "amstest";
        user.pwd = "Pwd123456";
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody reqBody = RequestBody.create(mediaType, String.valueOf(user));
        String next_hop = urlb + "/post";
        Request request = new Request.Builder().url(next_hop).post(reqBody).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return ExceptionUtil.getStackTrace(e);
        }
    }
}
