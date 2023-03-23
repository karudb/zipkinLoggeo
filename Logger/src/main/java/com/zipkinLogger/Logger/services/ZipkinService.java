package com.zipkinLogger.Logger.services;

public interface ZipkinService {
    public void send(String fieldName, Object data);

    public void sendResponse(Object response);
}
