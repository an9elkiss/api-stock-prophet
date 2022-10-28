package com.an9elkiss.api.spp.validator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TempValValidator.class)
public @interface TempVal {

    String message() default "字段无效{x.y}...";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
