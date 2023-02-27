package top.zwf666;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"top.zwf666"})
public class ActivityMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ActivityMain.class);
    }
}
