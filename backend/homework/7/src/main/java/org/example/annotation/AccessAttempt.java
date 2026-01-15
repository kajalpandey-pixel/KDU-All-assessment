package org.example.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessAttempt {
    String message() default "ACCESS ATTEMPT: User is approaching the door";
}
