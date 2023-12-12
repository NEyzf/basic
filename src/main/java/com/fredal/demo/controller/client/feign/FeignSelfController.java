package com.fredal.demo.controller.client.feign;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import feign.*;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/feign/self")
public class FeignSelfController {

    String next=System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");;
    String urlb = "http://" + next + ":80/";
    String urlp = System.getenv("ExternalAddr") == null ? "http://www.baidu.com" : System.getenv("ExternalAddr");
    FeignSelf clientb;
    PassThrough clientp;

    @PostConstruct
    public void init() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        ObjectFactory objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        ResponseEntityDecoder decoder = new ResponseEntityDecoder(new SpringDecoder(objectFactory));
        clientb = Feign.builder().decoder(decoder).target(FeignSelf.class, urlb);
        clientp = Feign.builder().decoder(decoder).target(PassThrough.class, urlp);
    }

    @RequestMapping(value = "/passthrough", method = RequestMethod.GET)
    public String passthrough() {
        return clientp.passthrough();
    }

    @RequestMapping(value = "/getBpost", method = RequestMethod.GET)
    public String getBpost() {
        return clientb.post();
    }

    @RequestMapping(value = "/getBdelete", method = RequestMethod.GET)
    public String getBdelete() {
        return clientb.delete();
    }

    @RequestMapping(value = "/getBhead", method = RequestMethod.GET)
    public String getBhead() {
        return clientb.head();
    }

    @RequestMapping(value = "/getBoptions", method = RequestMethod.GET)
    public String getBoptions() {
        return clientb.options();
    }

    @RequestMapping(value = "/getBput", method = RequestMethod.GET)
    public String getBput() {
        return clientb.put();
    }

    @RequestMapping(value = "/getBpatch", method = RequestMethod.GET)
    public String getBpatch() {
        return clientb.patch();
    }

    @RequestMapping(value = "/getBtrace", method = RequestMethod.GET)
    public String getBtrace() {
        return clientb.trace();
    }

    @RequestMapping(value = "/getBheadertest", method = RequestMethod.GET)
    public String getBheadertest() {
        return clientb.headertest();
    }

    @RequestMapping(value = "/getBrequestparamtest", method = RequestMethod.GET)
    public String getBrequestparamtest() {
        return clientb.requestparamtest(1000, "amstest");
    }

    @RequestMapping(value = "/getBpathvariabletest", method = RequestMethod.GET)
    public String getBpathvariabletest() {
        return clientb.pathvariabletest("amstest");
    }

    @RequestMapping(value = "/getBbodytest", method = RequestMethod.GET)
    public String getBbodytest() throws JSONException {
        JSONObject body = new JSONObject();
        body.put("pwd", "123456");
        body.put("name", "mse");
        return clientb.bodytest(body.toString());
    }

    public interface FeignSelf {
        @RequestLine("POST /post")
        String post();

        @RequestLine("DELETE /delete")
        String delete();

        @RequestLine("HEAD /head")
        String head();

        @RequestLine("OPTIONS /options")
        String options();

        @RequestLine("PUT /put")
        String put();

        @RequestLine("PATCH /patch")
        String patch();

        @RequestLine("TRACE /trace")
        String trace();

        @RequestLine("GET /headertest")
        @Headers({"req1: v1", "req2: v2", "req3: v3"})
        String headertest();

        @RequestLine("GET /requestparamtest?id={id}&name={name}")
        String requestparamtest(@Param("id") Integer id, @Param("name") String name);

        @RequestLine("GET /pathvariabletest/{name}")
        String pathvariabletest(@Param("name") String name);

        @RequestLine("POST /bodytest")
        @Headers({"req1: v1", "Accept: application/json", "Content-Type: application/json"})
        @Body("{body}")
        String bodytest(@Param("body") String body);
    }

    public interface PassThrough {
        @RequestLine("GET /")
        String passthrough();
    }
}
