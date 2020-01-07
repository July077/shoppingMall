package com.suny.taotao.manager.order;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author sunjianrong
 * @date 2020-01-07 15:09
 */
@EnableAutoConfiguration
@EnableAsync
public class OrderProvider {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OrderProvider.class).run(args);
    }
}