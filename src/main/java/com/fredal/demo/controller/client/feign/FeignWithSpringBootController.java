package com.fredal.demo.controller.client.feign;

import com.alibaba.fastjson.JSONException;
import com.fredal.demo.bean.User;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/feign/springboot")
public class FeignWithSpringBootController {


    @Autowired
    FeignWithSpring clientb;
    @Autowired
    PassThrough clientp;
    @Autowired
    GetB1 getb1;
    @Autowired
    GetB2 getb2;

    @RequestMapping(value = "/AtoB1", method = RequestMethod.GET)
    public String getB1() {
        return getb1.getB1();
    }

    @RequestMapping(value = "/AtoB2", method = RequestMethod.GET)
    public String getB2() {
        return getb2.getB2();
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
    public String getBbodytest() {
        User user = new User("mse", "12345");
        return clientb.bodytest(user);
    }

    @RequestMapping(value = "/getBUser", method = RequestMethod.GET)
    public User getBUser() {
        User user = new User("mse", "12345");
        return clientb.getUser(user);
    }

    // ignore_security_alert
    @PostMapping(value = "/getBupload", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String getBupload(@RequestPart MultipartFile file) {
        return clientb.upload(file);
    }

    ;

    @RequestMapping(value = "/getBdownload", method = RequestMethod.GET)
    public void getBdownload(@RequestParam String fileName, HttpServletResponse response) throws IOException {
        InputStream inputStream = null;
        Response serviceResponse = clientb.download(fileName);
        Response.Body body = serviceResponse.body();
        inputStream = body.asInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        response.setHeader("Content-Disposition", serviceResponse.headers().get("Content-Disposition").toString().replace("[", "").replace("]", ""));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        int length = 0;
        byte[] temp = new byte[1024 * 10];
        while ((length = bufferedInputStream.read(temp)) != -1) {
            bufferedOutputStream.write(temp, 0, length);
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedInputStream.close();
        inputStream.close();
    }

    @FeignClient(value = "tob", url = "http://" + "${next.application.name}" + ":80/")
    public interface FeignWithSpring {
        @RequestMapping(value = "/post", method = RequestMethod.POST)
        String post();

        @RequestMapping(value = "/get", method = RequestMethod.GET)
        String get();

        @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
        String delete();

        @RequestMapping(value = "/head", method = RequestMethod.HEAD)
        String head();

        @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
        String options();

        @RequestMapping(value = "/put", method = RequestMethod.PUT)
        String put();

        @RequestMapping(value = "/patch", method = RequestMethod.PATCH)
        String patch();

        @RequestMapping(value = "/headertest", headers = {"req1=v1", "req2=v2", "req3=v3"}, method = RequestMethod.GET)
        @ResponseBody
        String headertest();

        @RequestMapping(value = "/requestparamtest", method = RequestMethod.GET)
        String requestparamtest(@RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name);

        @RequestMapping(value = "/pathvariabletest/{name}", method = RequestMethod.GET)
        String pathvariabletest(@PathVariable("name") String name);

        @RequestMapping(value = "/bodytest", headers = {"req1=v1", "Accept=application/json", "Content-Type=application/json"}, method = RequestMethod.POST)
        String bodytest(@RequestBody User user);

        @RequestMapping(value = "/getUser", headers = {"req1=v1", "Accept=application/json", "Content-Type=application/json"}, method = RequestMethod.POST)
        User getUser(@RequestBody User user);

        // ignore_security_alert
        @PostMapping(value = "upload", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        String upload(@RequestPart MultipartFile file);

        @GetMapping(value = "/download", consumes = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
        Response download(@RequestParam String fileName);
    }

    @FeignClient(value = "passthrough", url = "${external.url}")
    public interface PassThrough {
        @RequestMapping(value = "/", method = RequestMethod.GET)
        String passthrough();
    }

    // 以下覆盖url的不同使用方式
    @FeignClient(value = "tob1", url = "http://" + "${next.application.name}" + ":80/feign/self")
    public interface GetB1 {
        @RequestMapping(value = "/getBpost", method = RequestMethod.GET)
        String getB1();
    }

    @FeignClient(value = "tob2", url = "http://" + "${next.application.name}" + ":80/feign/self/getBpost")
    public interface GetB2 {
        @RequestMapping(method = RequestMethod.GET)
        String getB2();
    }
}
