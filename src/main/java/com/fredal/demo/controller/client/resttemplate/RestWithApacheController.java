package com.fredal.demo.controller.client.resttemplate;

import com.fredal.demo.bean.User;
import com.fredal.demo.config.FacePlusThrowErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.springframework.http.HttpHeaders.CONNECTION;

@RestController
@RequestMapping("/rest/apache")
public class RestWithApacheController {

    @Autowired
    private RestTemplate restTemplateWithApache;
    String next = System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");
    String urlb = "http://" + next + ":80/";
    String urlp = System.getenv("ExternalAddr") == null ? "http://www.baidu.com" : System.getenv("ExternalAddr");

    @RequestMapping(value = "/passthrough", method = RequestMethod.GET)
    public String passthrough() {
        ResponseEntity<String> res;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(null, requestHeaders);
        res = restTemplateWithApache.exchange(urlp, HttpMethod.GET, httpEntity, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBpost", method = RequestMethod.GET)
    public String getBpost() {
        String next_hop = urlb + "post";
        ResponseEntity<String> res;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(null, requestHeaders);
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.POST, httpEntity, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBdelete", method = RequestMethod.GET)
    public String getBdelete() {
        String next_hop = urlb + "delete";
        ResponseEntity<String> res;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(null, requestHeaders);
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.DELETE, httpEntity, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBhead", method = RequestMethod.GET)
    public String getBhead() {
        String next_hop = urlb + "head";
        ResponseEntity<String> res;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(null, requestHeaders);
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.HEAD, httpEntity, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBoptions", method = RequestMethod.GET)
    public String getBoptions() {
        String next_hop = urlb + "options";
        ResponseEntity<String> res;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(null, requestHeaders);
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.OPTIONS, httpEntity, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBput", method = RequestMethod.GET)
    public String getBput() {
        String next_hop = urlb + "put";
        ResponseEntity<String> res;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(null, requestHeaders);
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.PUT, httpEntity, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBpatch", method = RequestMethod.GET)
    public String getBpatch() {
        String next_hop = urlb + "patch";
        ResponseEntity<String> res;
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.PATCH, HttpEntity.EMPTY, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBheadertest", method = RequestMethod.GET)
    public String getBheadertest() {
        String next_hop = urlb + "headertest";
        HttpHeaders headers = new HttpHeaders();//header参数
        headers.add("req1", "v1");
        headers.add("req2", "v2");
        headers.add("req3", "v3");
        headers.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<String> request = new HttpEntity(headers);
        ResponseEntity<String> res;
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.GET, request, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBrequestparamtest", method = RequestMethod.GET)
    public String getBrequestparamtest() {
        String next_hop = urlb + "requestparamtest";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(next_hop).
                queryParam("id", 1000).
                queryParam("name", "amstest").toUriString();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(null, requestHeaders);
        ResponseEntity<String> res;
        res = restTemplateWithApache.exchange(urlTemplate, HttpMethod.GET, httpEntity, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBpathvariabletest", method = RequestMethod.GET)
    public String getBpathvariabletest() {
        String next_hop = urlb + "pathvariabletest/amstest";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(null, requestHeaders);
        ResponseEntity<String> res;
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.GET, httpEntity, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBbodytest", method = RequestMethod.GET)
    public String getBbodytest() {
        String next_hop = urlb + "bodytest";
        HttpHeaders headers = new HttpHeaders();//header参数
        headers.add("req1", "v1");
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.put(CONNECTION, Collections.singletonList("close"));
        User user = new User("mse", "12345");
        HttpEntity<String> request = new HttpEntity(user, headers);
        ResponseEntity<String> res;
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.POST, request, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBUser", method = RequestMethod.GET)
    public User getBUser() {
        String next_hop = urlb + "getUser";
        HttpHeaders headers = new HttpHeaders();//header参数
        headers.add("req1", "v1");
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.put(CONNECTION, Collections.singletonList("close"));
        User user = new User("mse", "12345");
        HttpEntity<String> request = new HttpEntity(user, headers);
        ResponseEntity<User> res;
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.POST, request, User.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }
}
