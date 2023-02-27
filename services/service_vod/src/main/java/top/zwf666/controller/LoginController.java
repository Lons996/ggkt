package top.zwf666.controller;

import org.springframework.web.bind.annotation.*;
import top.zwf666.mycreatebean.util.Message;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/vod/user")
//@CrossOrigin
public class LoginController {

    @PostMapping("login")
    public Message login() {
        Message m = new Message();
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        m.setData(map);
        m.setCode(20000);
        return m;
    }

    @GetMapping("info")
    public Message info() {
        Message m = new Message();
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        map.put("roles","admin");
        map.put("introduction","I am a super administrator");
        map.put("name","Super Admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

        m.setData(map);
        m.setCode(20000);
        return m;
    }
}
