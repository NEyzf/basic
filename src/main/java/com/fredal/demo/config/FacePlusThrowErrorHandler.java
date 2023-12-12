package com.fredal.demo.config;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class FacePlusThrowErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response)  {
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse response)  {

    }
}