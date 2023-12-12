package com.fredal.demo.controller.server;

import lombok.val;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping
public class HealthServer {
    @RequestMapping(value = "/status")
    public ResponseEntity<String> status(@RequestParam("status") Integer status) {
        HttpStatus httpStatus = HttpStatus.resolve(status);
        return ResponseEntity.status(httpStatus).body(httpStatus.toString());
    }
}
