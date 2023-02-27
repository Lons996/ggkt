package top.zwf666;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.zwf666.fallback.UserInfoFallback;
import top.zwf666.mycreatebean.util.Message;

@FeignClient(value = "service-user")
@Component
public interface UserInfoClient {

    //通过userId 获取用户数据
    @GetMapping("/user/getUser/{userId}")
    Message getUser(@PathVariable Long userId);

}
