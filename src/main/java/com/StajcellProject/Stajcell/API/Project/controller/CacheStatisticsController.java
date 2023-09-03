package com.StajcellProject.Stajcell.API.Project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import com.StajcellProject.Stajcell.API.Project.Aspect.LoggingAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.CacheManager;


@RestController
@RequestMapping("/cache-statistics")
public class CacheStatisticsController {


    private LoggingAspect loggingAspect;

    public CacheStatisticsController(LoggingAspect loggingAspect) {
        this.loggingAspect = loggingAspect;
    }
    @GetMapping("/getMessage")
    public String getCacheStatistics() {
       
         return this.loggingAspect.getLogMessage();
    }
}


