package top.zwf666;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@EnableDiscoveryClient //使用@EnableDiscoveryClient注解打开服务注册和发现
public class VodTeacherMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(VodTeacherMain.class);
    }
}
