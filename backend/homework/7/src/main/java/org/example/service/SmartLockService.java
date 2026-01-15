package org.example.service;
import org.example.exception.HardwareFailureException;

import org.example.annotation.AccessAttempt;
import org.example.annotation.Stopwatch;
import org.example.annotation.Success;
import org.springframework.stereotype.Service;

@Service
public class SmartLockService {

    @AccessAttempt
    @Success
    public void unlock(String user) {

        if (user == null || user.isEmpty()) {
            throw new HardwareFailureException("Hardware failure detected");

        }
            System.out.println("The door is now open for " + user);
    }


    @Stopwatch
    public void checkBattery(){
        System.out.println("Checking battery");

        try{
           Thread.sleep(500) ;
        }
        catch(Exception e){
            System.out.println(e + "this error occured") ;
        }


    }





}
