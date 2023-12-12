package com.fredal.demo.controller.client.feign;

import com.alibaba.fastjson.JSONException;
import com.fredal.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feign/ribbon")
public class FeignWithRibbonController {

    @Autowired
    FeignWithRibbon clientb;

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
    public String getBbodytest() throws JSONException {
        User user = new User("mse", "12345");
        return clientb.bodytest(user);
    }

    @RequestMapping(value = "/getBUser", method = RequestMethod.GET)
    public User getBUser() {
        User user = new User("mse", "12345");
        return clientb.getUser(user);
    }

    @FeignClient(value = "${next.application.name}")
    public interface FeignWithRibbon {
        @RequestMapping(value = "/post", method = RequestMethod.POST)
        String post();

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

        @RequestMapping(value = "/feign/getnext", method = RequestMethod.GET)
        String getfeignext(@RequestHeader(required = false) HttpHeaders headers);
    }
}
