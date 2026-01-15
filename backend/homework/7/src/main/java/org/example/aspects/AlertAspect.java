package org.example.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AlertAspect {

    // Catch any exception thrown by ANY method in SmartLockService
    @AfterThrowing(
            pointcut = "execution(* org.example.service.SmartLockService.*(..))",
            throwing = "ex"
    )
    public void triggerAlarm(Exception ex) {
        System.out.println("ALARM TRIGGERED: System error detected: " + ex.getMessage());
        callMockEmergencyService(ex);
    }

    private void callMockEmergencyService(Exception ex) {
        System.out.println("Mock Emergency Service Called! (reason: " + ex.getClass().getSimpleName() + ")");
    }
}
