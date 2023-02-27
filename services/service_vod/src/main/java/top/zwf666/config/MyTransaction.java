package top.zwf666.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 事务管理
 */

@Configuration
@EnableTransactionManagement //开启事务注解
public class MyTransaction {
}
