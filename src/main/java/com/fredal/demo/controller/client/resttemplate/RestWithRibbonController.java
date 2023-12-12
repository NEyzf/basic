package com.fredal.demo.controller.client.resttemplate;

import com.fredal.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/rest/ribbon")
public class RestWithRibbonController {

    @Autowired
    private RestTemplate restTemplateWithRibbon;
    String next=System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");;
    String urlb = "http://" + next + ":80/";
    String urlp = System.getenv("ExternalAddr") == null ? "http://www.baidu.com" : System.getenv("ExternalAddr");

    @RequestMapping(value = "/passthrough", method = RequestMethod.GET)
    public String passthrough() {
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(urlp, HttpMethod.GET,  HttpEntity.EMPTY, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBpost", method = RequestMethod.GET)
    public String getBpost() {
        String next_hop = urlb + "post";
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(next_hop, HttpMethod.POST, HttpEntity.EMPTY, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBdelete", method = RequestMethod.GET)
    public String getBdelete() {
        String next_hop = urlb + "delete";
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(next_hop, HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBhead", method = RequestMethod.GET)
    public String getBhead() {
        String next_hop = urlb + "head";
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(next_hop, HttpMethod.HEAD, HttpEntity.EMPTY, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBoptions", method = RequestMethod.GET)
    public String getBoptions() {
        String next_hop = urlb + "options";
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(next_hop, HttpMethod.OPTIONS, HttpEntity.EMPTY, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBput", method = RequestMethod.GET)
    public String getBput() {
        String next_hop = urlb + "put";
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(next_hop, HttpMethod.PUT, HttpEntity.EMPTY, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBpatch", method = RequestMethod.GET)
    public String getBpatch() {
        String next_hop = urlb + "patch";
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(next_hop, HttpMethod.PATCH, HttpEntity.EMPTY, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBheadertest", method = RequestMethod.GET)
    public String getBheadertest() {
        String next_hop = urlb + "headertest";
        HttpHeaders headers = new HttpHeaders();//header参数
        headers.add("req1","v1");
        headers.add("req2","v2");
        headers.add("req3","v3");
        HttpEntity<String> request = new HttpEntity(headers);
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(next_hop, HttpMethod.GET, request, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBrequestparamtest", method = RequestMethod.GET)
    public String getBrequestparamtest() {
        String next_hop = urlb + "requestparamtest";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(next_hop).
                queryParam("id",1000).
                queryParam("name","amstest").toUriString();
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(urlTemplate, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBpathvariabletest", method = RequestMethod.GET)
    public String getBpathvariabletest() {
        String next_hop = urlb + "pathvariabletest/amstest";
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(next_hop, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBbodytest", method = RequestMethod.GET)
    public String getBbodytest() {
        String next_hop = urlb + "bodytest";
        HttpHeaders headers = new HttpHeaders();//header参数
        headers.add("req1","v1");
        headers.add("Accept","application/json");
        headers.add("Content-Type","application/json");
        User user = new User("mse", "12345");
        HttpEntity<String> request = new HttpEntity(user,headers);
        ResponseEntity<String> res;
        res = restTemplateWithRibbon.exchange(next_hop, HttpMethod.POST, request, String.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }

    @RequestMapping(value = "/getBUser", method = RequestMethod.GET)
    public User getBUser() {
        String next_hop = urlb + "getUser";
        HttpHeaders headers = new HttpHeaders();//header参数
        headers.add("req1","v1");
        headers.add("Accept","application/json");
        headers.add("Content-Type","application/json");
        User user = new User("mse", "12345");
        HttpEntity<String> request = new HttpEntity(user,headers);
        ResponseEntity<User> res;
        res = restTemplateWithRibbon.exchange(next_hop, HttpMethod.POST, request, User.class);
        if (res.getStatusCodeValue() != 200) {
            throw new RuntimeException(res.getStatusCode().getReasonPhrase());
        }
        return res.getBody();
    }
}
