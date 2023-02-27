package top.zwf666;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class UserMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(UserMain.class);
    }
}
