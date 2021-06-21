package com.xy.sample2;

import com.sc.common.rmq.tx.service.impl.RmqTxServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.xy", "com.sc"}, excludeFilters = {@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = {RmqTxServiceImpl.class})})
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients("com.xy")
@EnableRetry
public class Sample2Application {

    public static void main(String[] argv) {
        SpringApplication.run(Sample2Application.class, argv);
    }
}
