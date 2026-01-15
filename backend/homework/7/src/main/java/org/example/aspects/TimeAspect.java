package org.example.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.annotation.PasscodeCheck;
import org.example.annotation.Stopwatch;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

    @Around("@annotation(stopwatch)")
    public Object timeMeasure(ProceedingJoinPoint pjp, Stopwatch stopwatch)
            throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        // this means -----method is running  ;
        long end = System.currentTimeMillis();
        System.out.println("This long " + (end - start) + " check takes time ");
        return result;


    }


    @Around("@annotation(passcodeCheck)")
    public Object passcodeGate(ProceedingJoinPoint pjp, PasscodeCheck passcodeCheck)
            throws Throwable {
        Object[] args = pjp.getArgs();
        String user = (args != null && args.length > 0) ? String.valueOf(args[0]) : "Unknown";

        if ("Unknown".equalsIgnoreCase(user)) {
            System.out.println("SECURITY ALERT: Unauthorized access blocked!");
            return null; // IMPORTANT: do not call proceed()
        }

        // If Guest (or anyone allowed), proceed
        return pjp.proceed();

    }


}

