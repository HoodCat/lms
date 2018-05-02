package com.cafe24.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.cafe24.lms.domain.enumeration.Role;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Auth {
    public Role role() default Role.USER;
}
