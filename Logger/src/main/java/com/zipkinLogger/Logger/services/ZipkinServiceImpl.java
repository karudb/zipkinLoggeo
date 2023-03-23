package com.zipkinLogger.Logger.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.SpanCustomizer;
import org.springframework.stereotype.Service;

@Service
public class ZipkinServiceImpl implements ZipkinService {

    private SpanCustomizer spanCustomizer;

    @Autowired
    public ZipkinServiceImpl(SpanCustomizer spanCustomizer) {
        this.spanCustomizer = spanCustomizer;
    }

    @Override
    public void send(String fieldName, Object data) {
        spanCustomizer.tag(fieldName, toJson(data));
    }

    @Override
    public void sendResponse(Object response) {
        spanCustomizer.tag("response", toJson(response));
    }

    private String toJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            return String.format("{ \"error\": %s }", e.getMessage());
        }
    }
}