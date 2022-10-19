package com.an9elkiss.api.spp.api;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class RegionPrivilegeAspect {
    @Pointcut("@annotation(RegionPrivilege)")
    public void f() {}

    @Before("f()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            return;
        }

        Field field = ReflectionUtils.findField(args[0].getClass(), "shopIds");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, args[0], Arrays.asList(1l,2l));


        log.info("aaaa");
    }

}
