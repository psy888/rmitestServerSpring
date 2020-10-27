package com.rmi.rmitest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;

import java.rmi.Remote;
import java.rmi.RemoteException;

@SpringBootApplication
public class RmitestApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(RmitestApplication.class, args);
    }

    @Bean
    public RmiServiceExporter rmiServiceExporter(){
        RmiServiceExporter rmi = new RmiServiceExporter();
        rmi.setServiceName("myRemoteObj");
        rmi.setService(new MyRemoteObject());
        rmi.setServiceInterface(MyRemoteInterface.class);
        rmi.setRegistryPort(1099);
        return rmi;
    }
}

interface MyRemoteInterface extends Remote
{
    Integer getIncreasedNumber(Integer num) throws RemoteException;
}

class MyRemoteObject implements MyRemoteInterface
{

    @Override
    public Integer getIncreasedNumber(final Integer num) throws RemoteException
    {
        System.out.println("REMOTE INVOCATION WITH PARAM : " + num);
        return num + 1;
    }
}