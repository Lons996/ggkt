package top.zwf666.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.service.UserInfoService;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    //通过userId 获取用户数据
    @GetMapping("/getUser/{userId}")
    public Message getUser(@PathVariable Long userId) {
        return userInfoService.getUser(userId);
    }

}
