package com.fredal.demo.controller.server;

import com.fredal.demo.controller.client.feign.FeignWithRibbonController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.CONNECTION;

@Slf4j
@RestController
@RequestMapping
public class TrafficRule {
    @Autowired
    private RestTemplate restTemplateWithApache;
    @Autowired
    FeignWithRibbonController.FeignWithRibbon clientnext;

    String next = System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");
    String urlb = "http://" + next + "/";
    @RequestMapping(value = "/forward")
    public String forward(HttpServletRequest httpServletRequest, @RequestHeader(required = false) HttpHeaders headers,
                          @RequestParam("target") String target, @RequestParam(value = "sleep", required = false, defaultValue = "0") String sleep_time,
                          @RequestBody(required = false) String body) throws InterruptedException {
        headers.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> res;
        Thread.sleep(Integer.parseInt(sleep_time));
        res = restTemplateWithApache.exchange(target, Objects.requireNonNull(HttpMethod.resolve(httpServletRequest.getMethod())), request, String.class);
        return res.getBody();
    }

    @RequestMapping(value = "/getBPost", method = RequestMethod.GET)
    public String getBPost(@RequestHeader HttpHeaders headers) {
        String next_hop = urlb + "post";
        ResponseEntity<String> res;
        headers.put(CONNECTION, Collections.singletonList("close"));
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(null, headers);
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.POST, httpEntity, String.class);
        return res.getBody();
    }

    @RequestMapping(value = "/getBstatus", method = RequestMethod.GET)
    public String getB504(@RequestParam("status") Integer status, @RequestHeader HttpHeaders headers) {
        String next_hop = urlb + "status";
        HttpEntity request = new HttpEntity(headers);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(next_hop).queryParam("status", status).toUriString();
        ResponseEntity<String> res;
        res = restTemplateWithApache.exchange(urlTemplate, HttpMethod.GET, request, String.class);
        return res.getBody();
    }

    @RequestMapping(value = "/getBsleep/{time}", method = RequestMethod.GET)
    public String getBsleep(@PathVariable("time") int sleep_time, @RequestHeader HttpHeaders headers) {
        String next_hop = urlb + "sleep/" + sleep_time;
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> res;
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.GET, request, String.class);
        return res.getBody();
    }

    @RequestMapping(value = "/feign/getnext")
    public String getfeignext(@RequestHeader(required = false) HttpHeaders headers) {
        String cur_hop_info = System.getenv("CurServiceName");
        String pass_through = System.getenv("PaasThrough")== null ? "true" : System.getenv("PaasThrough");
        String cur_hop_version = System.getenv("CurServiceVersion") == null ? "v1" : System.getenv("CurServiceVersion");
        String next_service_name = System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");
        log.info("the server get header info is "+ headers.toString());

        if (headers.containsKey("baggage")) {
            headers.remove("baggage");
        }
        if (headers.containsKey("x-mse-http-request-injected")) {
            headers.remove("x-mse-http-request-injected");
        }
        String response, next_hop_info = "";

        if (next_service_name.equals("localhost")) {
          return cur_hop_info + " " + cur_hop_version + " -> end";
        } else {
          if (pass_through=="true"){
            response = clientnext.getfeignext(headers);
          }else {
            response = clientnext.getfeignext(new HttpHeaders());
          }
          next_hop_info = next_hop_info + response;
        }
        return cur_hop_info + " " + cur_hop_version + " -> " + next_hop_info;
    }

    @RequestMapping(value = "/getnext")
    public String getnext(@RequestHeader(required = false) HttpHeaders headers) {
        String cur_hop_info = System.getenv("CurServiceName");
        String pass_through = System.getenv("PaasThrough")== null ? "true" : System.getenv("PaasThrough");
        String cur_hop_version = System.getenv("CurServiceVersion") == null ? "v1" : System.getenv("CurServiceVersion");
        String next_service_name = System.getenv("NextServiceName") == null ? "localhost" : System.getenv("NextServiceName");
        log.info("the server get header info is "+ headers.toString());


        if (headers.containsKey("baggage")) {
            headers.remove("baggage");
        }
        if (headers.containsKey("x-mse-http-request-injected")) {
            headers.remove("x-mse-http-request-injected");
        }
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> responseEntity;
        String next_hop, next_hop_info = "";

        if (next_service_name.equals("localhost")) {
            return cur_hop_info + " " + cur_hop_version + " -> end";
        } else {
            for (String name_temp : next_service_name.split(",")) {
                next_hop = "http://" + name_temp + ":80/getnext";
                if (pass_through=="true"){
                    responseEntity = restTemplateWithApache.exchange(next_hop, HttpMethod.GET, request, String.class);
                }else {
                    responseEntity = restTemplateWithApache.exchange(next_hop, HttpMethod.GET, null, String.class);
                }
                next_hop_info = next_hop_info + responseEntity.getBody();
            }
        }
        return cur_hop_info + " " + cur_hop_version + " -> " + next_hop_info;
    }

    @RequestMapping(value = "/getBLoop", method = RequestMethod.GET)
    public String getBLoop1(@RequestHeader HttpHeaders headers) {
        String next_hop = urlb + "getSelfPost";
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> res;
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.GET, request, String.class);
        return res.getBody();
    }

    @RequestMapping(value = "/getSelfPost", method = RequestMethod.GET)
    public String getSelfPost() {
        String next_hop = "http://" + System.getenv("CurServiceName") + "/post";
        ResponseEntity<String> res;
        res = restTemplateWithApache.exchange(next_hop, HttpMethod.POST, HttpEntity.EMPTY, String.class);
        return res.getBody();
    }
}

