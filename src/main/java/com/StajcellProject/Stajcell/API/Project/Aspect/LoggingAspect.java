package com.StajcellProject.Stajcell.API.Project.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.CacheManager; // Eksik olan import
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired; // Eksik olan import
import com.github.benmanes.caffeine.cache.Cache;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CacheManager cacheManager; // CacheManager enjekte ediliyor

    @AfterReturning(
        pointcut = "execution(* com.StajcellProject.Stajcell.API.Project..*.*(..)) && @annotation(cacheable)",
        returning = "result"
    )
    public void logCacheableMethodInvocation(JoinPoint joinPoint, Cacheable cacheable, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String cacheName = cacheable.value()[0]; // Cacheable annotation's value attribute

        logger.info("Cacheable method '{}' invoked with cache name: '{}', result: '{}'", methodName, cacheName);
        logCacheSize(cacheName); // Cache boyutunu logla
    }

    @AfterReturning(
        pointcut = "execution(* com.StajcellProject.Stajcell.API.Project.Aspect..*.*(..)) && @annotation(org.springframework.cache.annotation.CacheEvict)",
        returning = "result"
    )
    public void logCacheEvictMethodInvocation(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String cacheName = joinPoint.getArgs()[0].toString(); // Assuming cache name is the first parameter

        logger.info("CacheEvict method '{}' invoked with cache name: '{}'", methodName, cacheName);
    }


    private void logCacheSize(String cacheName) {
        Cache<Object, Object> cache = (Cache<Object, Object>) cacheManager.getCache(cacheName).getNativeCache();
        long cacheSize = cache.estimatedSize();
        logger.info("Cache '{}' size: {}", cacheName, cacheSize);
    }


}
