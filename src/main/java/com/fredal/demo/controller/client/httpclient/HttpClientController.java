package com.fredal.demo.controller.client.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.nacos.common.utils.ExceptionUtil;
import com.fredal.demo.bean.User;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/httpclient")
public class HttpClientController {

    CloseableHttpClient httpclient;
    String next = System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");
    String urlb = "http://" + next + ":80/";
    String urlp = System.getenv("ExternalAddr") == null ? "http://www.baidu.com" : System.getenv("ExternalAddr");

    @PostConstruct
    public void init() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //set cookie spec: compatible with apache httpclient 4.x
        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);
        // set connection pool
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(10);
        httpClientBuilder.setConnectionManager(cm);
        // use system property config
        httpClientBuilder.useSystemProperties();

        httpclient = httpClientBuilder.build();
    }

    @RequestMapping(value = "/passthrough", method = RequestMethod.GET)
    public String passthrough() {
        HttpPost httpReq = new HttpPost(urlp);
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBpost", method = RequestMethod.GET)
    public String getBpost() {
        String next_hop = urlb + "/post";
        HttpPost httpReq = new HttpPost(next_hop);
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBdelete", method = RequestMethod.GET)
    public String getBdelete() {
        String next_hop = urlb + "/delete";
        HttpDelete httpReq = new HttpDelete(next_hop);
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBhead", method = RequestMethod.GET)
    public String getBhead() {
        String next_hop = urlb + "/head";
        HttpHead httpReq = new HttpHead(next_hop);
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return "return code: " + response.getStatusLine().getStatusCode();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBoptions", method = RequestMethod.GET)
    public String getBoptions() {
        String next_hop = urlb + "/options";
        HttpOptions httpReq = new HttpOptions(next_hop);
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBput", method = RequestMethod.GET)
    public String getBput() {
        String next_hop = urlb + "/put";
        HttpPut httpReq = new HttpPut(next_hop);
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBpatch", method = RequestMethod.GET)
    public String getBpatch() {
        String next_hop = urlb + "/patch";
        HttpPatch httpReq = new HttpPatch(next_hop);
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBtrace", method = RequestMethod.GET)
    public String getBtrace() {
        String next_hop = urlb + "/trace";
        HttpTrace httpReq = new HttpTrace(next_hop);
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBheadertest", method = RequestMethod.GET)
    public String getBheadertest() {
        String next_hop = urlb + "/headertest";
        HttpGet httpReq = new HttpGet(next_hop);
        httpReq.setHeader("req1", "v1");
        httpReq.setHeader("req2", "v2");
        httpReq.setHeader("req3", "v3");
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBrequestparamtest", method = RequestMethod.GET)
    public String getBrequestparamtest() {
        String next_hop = urlb + "/requestparamtest?id=10000&&name=amstest";
        HttpGet httpReq = new HttpGet(next_hop);
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBpathvariabletest", method = RequestMethod.GET)
    public String getBpathvariabletest() {
        String next_hop = urlb + "/pathvariabletest/amstest";
        HttpGet httpReq = new HttpGet(next_hop);
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }

    @RequestMapping(value = "/getBbodytest", method = RequestMethod.GET)
    public String getBbodytest() throws JSONException, UnsupportedEncodingException {
        String next_hop = urlb + "/bodytest";
        HttpPost httpReq = new HttpPost(next_hop);
        User user = new User();
        user.name = "amstest";
        user.pwd = "Pwd123456";
        String jsonString = JSON.toJSONString(user);
        StringEntity s = new StringEntity(jsonString);
        httpReq.setEntity(s);
        httpReq.setHeader("req1", "v1");
        httpReq.setHeader("Accept", "application/json");
        s.setContentType("application/json");
        try (CloseableHttpResponse response = httpclient.execute(httpReq)) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ExceptionUtil.getStackTrace(throwable);
        }
    }
}
