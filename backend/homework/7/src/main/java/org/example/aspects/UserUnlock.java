package org.example.aspects;

import org.example.annotation.AccessAttempt;
import org.example.annotation.Success;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserUnlock {

    @Before("@annotation(accessAttempt)")
    public void logAttempt(AccessAttempt accessAttempt) {
        System.out.println(accessAttempt.message());
    }

    @AfterReturning("@annotation(success)")
    public void logSuccess(Success success) {
        System.out.println(success.message());
    }
}
