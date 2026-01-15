package org.example.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Success {
    String message() default "SUCCESS: User has entered the building";
}
