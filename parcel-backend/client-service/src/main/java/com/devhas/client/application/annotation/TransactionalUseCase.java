package com.devhas.client.application.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionalUseCase {
    boolean readOnly() default false;
    int timeout() default -1;
    String[] rollbackFor() default {};
}
