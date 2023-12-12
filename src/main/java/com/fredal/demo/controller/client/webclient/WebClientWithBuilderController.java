package com.fredal.demo.controller.client.webclient;

import com.fredal.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/webclient/ribbon")
public class WebClientWithBuilderController {

    @Autowired
    private WebClient.Builder webClientBuilder;
    String next = System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");
    String urlb = "http://" + next + ":80/";
    String urlb1 = "http://" + next + ":80/feign/self/";
    String urlb2 = "http://" + next + ":80/feign/self/getBpost/";

    @RequestMapping(value = "/AtoB1", method = RequestMethod.GET)
    public String getB1() {
        return webClientBuilder.build().get().uri(urlb1 + "/getBpost").retrieve().bodyToMono(String.class).block();
    }

    @RequestMapping(value = "/AtoB2", method = RequestMethod.GET)
    public String getB2() {
        return webClientBuilder.build().get().uri(urlb2).retrieve().bodyToMono(String.class).block();
    }

    @GetMapping(value = "/getBpost")
    public String getBpost() {
        return webClientBuilder.build().post().uri(urlb + "/post").retrieve().bodyToFlux(String.class).blockFirst();
    }

    @GetMapping(value = "/getBdelete")
    public String getBdelete() {
        return webClientBuilder.build().delete().uri(urlb + "/delete").retrieve().bodyToFlux(String.class).blockFirst();
    }

    @GetMapping(value = "/getBhead")
    public String getBhead() {
        return webClientBuilder.build().head().uri(urlb + "/head").retrieve().bodyToFlux(String.class).blockFirst();
    }

    @GetMapping(value = "/getBoptions")
    public String getBoptions() {
        return webClientBuilder.build().options().uri(urlb + "/options").retrieve().bodyToFlux(String.class).blockFirst();
    }

    @GetMapping(value = "/getBput")
    public String getBput() {
        return webClientBuilder.build().put().uri(urlb + "/put").retrieve().bodyToFlux(String.class).blockFirst();
    }

    @GetMapping(value = "/getBpatch")
    public String getBpatch() {
        return webClientBuilder.build().patch().uri(urlb + "/patch").retrieve().bodyToFlux(String.class).blockFirst();
    }

    @GetMapping(value = "/getBheadertest")
    public String getBheadertest() {
        return webClientBuilder.build().get().uri(urlb + "/headertest")
                .header("req1", "v1")
                .header("req2", "v2")
                .header("req3", "v3")
                .retrieve().bodyToFlux(String.class).blockFirst();
    }

    @GetMapping(value = "/getBrequestparamtest")
    public String getBrequestparamtest() {
        return webClientBuilder.build().get()
                .uri(urlb + "/requestparamtest?id={id}&&name={name}", 33333, "amstest222")
                .retrieve().bodyToFlux(String.class).blockFirst();
    }

    @GetMapping(value = "/getBpathvariabletest")
    public String getBpathvariabletest() {
        return webClientBuilder.build().get()
                .uri(urlb + "/pathvariabletest/{name}", "amstest")
                .retrieve().bodyToFlux(String.class).blockFirst();
    }

    @GetMapping(value = "/getBUser")
    public String getBUser() {
        User user = new User("mse", "12345");
        return webClientBuilder.build().post().uri(urlb + "/getUser")
                .header("req1", "v1")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .bodyValue(user)
                .retrieve().bodyToMono(User.class).block().toString();
    }

}

