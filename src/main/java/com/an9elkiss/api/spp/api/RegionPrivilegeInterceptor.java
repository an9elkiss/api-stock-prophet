package com.an9elkiss.api.spp.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RegionPrivilegeInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

//            RegionPrivilege regionPrivilege =
//                    handlerMethod.getMethodAnnotation(RegionPrivilege.class);
//
//            if (null == regionPrivilege || StringUtils.isEmpty(regionPrivilege.value())) {
//                return true;
//            }
//
//            String dtoName = regionPrivilege.value();
//
//            Arrays.stream(handlerMethod.getMethodParameters()).filter(p->p.getParameterName().equals(dtoName));

        }
        log.info("xxxxxxxxxxxxx");
        return true;
    }
}
