package com.zipkinLogger.Logger.services;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {

    @Autowired
    private ZipkinService zipkinService;

    @Before("@annotation(com.zipkinLogger.Logger.services.LogMethod)")
    public void sendRequest(JoinPoint joinPoint) {
        zipkinService.send("Request-" + joinPoint.getSignature().getName().toString(), joinPoint.getArgs());
        System.out.println("method: " + joinPoint.getSignature().getName());
        System.out.println("ARGs: " + joinPoint.getArgs());
    }


    @After("@annotation(com.zipkinLogger.Logger.services.LogMethod)")
    public void sendResponse(JoinPoint joinPoint) {
        zipkinService.sendResponse(joinPoint.getArgs());
        System.out.println("method: " + joinPoint.getSignature().getName());
        System.out.println("ARGs: " + joinPoint.getArgs());
    }
}

