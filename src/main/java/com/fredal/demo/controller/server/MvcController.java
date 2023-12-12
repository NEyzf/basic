package com.fredal.demo.controller.server;

import com.fredal.demo.bean.User;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
public class MvcController {
    InetAddress address = InetAddress.getLocalHost();
    String cur_host = address.getHostName();
    String cur_service = System.getenv("CurServiceName");
    String cur_version = System.getenv("CurServiceVersion") == null ? "v1" : System.getenv("CurServiceVersion");
    String output = "name: " + cur_service + " version: " + cur_version + "  host: " + cur_host;

    public MvcController() throws UnknownHostException {
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post() {
        log.info("this is a post request");
        return output + " action: post";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() {
        log.info("this is a get request");
        return output + " action: get";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete() {
        log.info("this is a delete request");
        return output + " action: delete";
    }

    @RequestMapping(value = "/head", method = RequestMethod.HEAD)
    public String head() {
        log.info("this is a head request");
        return output + " action: head";
    }

    @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
    public String options() {
        log.info("this is a options request");
        return output + " action: options";
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public String put() {
        log.info("this is a put request");
        return output + " action: put";
    }

    @RequestMapping(value = "/trace", method = RequestMethod.TRACE)
    public String trace() {
        log.info("this is a trace request");
        return output + " action: trace";
    }

    @RequestMapping(value = "/patch", method = RequestMethod.PATCH)
    public String patch() {
        log.info("this is a patch request");
        return output + " action: patch";
    }

    @RequestMapping(value = "/headertest", headers = {"req1=v1", "req2=v2", "req3=v3"}, method = RequestMethod.GET)
    @ResponseBody
    public String headertest() {
        return output + " action: multi header test";
    }

    @RequestMapping(value = "/requestparamtest", method = RequestMethod.GET)
    public String requestparamtest(@RequestParam Integer id, @RequestParam String name) {
        return output + " hello id: " + id + " hello name: " + name;
    }

    @RequestMapping(value = "/pathvariabletest/{name}", method = RequestMethod.GET)
    public String pathvariabletest(@PathVariable("name") String name) {
        return output + " hello name:" + name;
    }

    @RequestMapping(value = "/bodytest", headers = {"req1=v1"}, method = RequestMethod.POST)
    @ResponseBody
    public String bodytest(@RequestBody User user) {
        return output + " hello name:" + user.name + " hello pwd:" + user.pwd;
    }

    @RequestMapping(value = "/getUser", headers = {"req1=v1"}, method = RequestMethod.POST)
    @ResponseBody
    public User getUser(@RequestBody User user) {
        return user;
    }

    @RequestMapping(value = "/sleep/{time}", method = RequestMethod.GET)
    public String getSleep(@PathVariable("time") int time) throws InterruptedException {
        int sleep_time = time;
        Thread.sleep(sleep_time);
        return output;
    }
}
