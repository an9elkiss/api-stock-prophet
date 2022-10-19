package com.an9elkiss.api.spp.api;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RegionPrivilege {

    /**
     * 需要被注入shopIds的DTO名称
     * @return
     */
    String value() default "";
}
