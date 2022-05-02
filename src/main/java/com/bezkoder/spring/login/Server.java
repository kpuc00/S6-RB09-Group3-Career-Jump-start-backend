package com.bezkoder.spring.login;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Server {

//    @RabbitListener(queues = "${queue.name}")
//    public long factorial(int n) {
//        System.out.println("Received request for " + n);
//
//        long result = computeFactorial(n);
//
//        System.out.println("Returned " + result);
//
//        return result;
//    }

//    @RabbitListener(queues = "${queue.name}")
//    public String factorial(String m) {
//        System.out.println("Received from client" + m);
//
////       long result = computeFactorial(m.length());
//        System.out.println(m.length());
//        System.out.println("Received and confirmed");
//
//        return m + " E ODOBREN !!!";
//    }

    public long computeFactorial(int number) {
        long result = 1;

        for (int f = 2; f <= number; f++) {
            result *= f;
        }

        return result;
    }

}