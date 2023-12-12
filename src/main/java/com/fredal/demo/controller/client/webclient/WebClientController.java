package com.fredal.demo.controller.client.webclient;

import com.fredal.demo.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/webclient")
public class WebClientController {

    @Autowired
    private WebClient webClient;
    String next = System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");
    String urlb = "http://" + next + ":80/";
    String urlb1 = "http://" + next + ":80/feign/self/";
    String urlb2 = "http://" + next + ":80/feign/self/getBpost/";
    String urlp = System.getenv("ExternalAddr") == null ? "http://www.baidu.com/" : System.getenv("ExternalAddr");

    @RequestMapping(value = "/passthrough", method = RequestMethod.GET)
    public String passthrough() {
        return webClient.get().uri(urlp).retrieve().bodyToMono(String.class).block();
    }

    @RequestMapping(value = "/AtoB1", method = RequestMethod.GET)
    public String getB1(@RequestHeader HttpHeaders headers) {
        return webClient.get().uri(urlb1 + "/getBpost").headers(httpHeaders -> httpHeaders.addAll(headers)).retrieve().bodyToMono(String.class).block();
    }

    @RequestMapping(value = "/AtoB2", method = RequestMethod.GET)
    public String getB2() {
        return webClient.get().uri(urlb2).retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBpost")
    public String getBpost() {
        return webClient.post().uri(urlb + "/post").retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBdelete")
    public String getBdelete() {
        return webClient.delete().uri(urlb + "/delete").retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBhead")
    public String getBhead() {
        return webClient.head().uri(urlb + "/head").retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBoptions")
    public String getBoptions() {
        return webClient.options().uri(urlb + "/options").retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBput")
    public String getBput() {
        return webClient.put().uri(urlb + "/put").retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBpatch")
    public String getBpatch() {
        return webClient.patch().uri(urlb + "/patch").retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBheadertest")
    public String getBheadertest() {
        return webClient.get().uri(urlb + "/headertest")
                .header("req1", "v1")
                .header("req2", "v2")
                .header("req3", "v3")
                .retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBrequestparamtest")
    public String getBrequestparamtest() {
        return webClient.get()
                .uri(urlb + "/requestparamtest?id={id}&&name={name}", 33333, "amstest222")
                .retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBpathvariabletest")
    public String getBpathvariabletest() {
        return webClient.get()
                .uri(urlb + "/pathvariabletest/{name}", "amstest")
                .retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBUser")
    public String getBUser() {
        User user = new User("mse", "12345");
        return webClient.post().uri(urlb + "/getUser")
                .header("req1", "v1")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .bodyValue(user)
                .retrieve().bodyToMono(User.class).block().toString();
    }

}
