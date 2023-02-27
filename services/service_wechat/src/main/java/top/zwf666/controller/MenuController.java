package top.zwf666.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.wechat_entity.Menu;
import top.zwf666.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/wechat")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //获取所有菜单，按照一级和二级菜单封装
    @GetMapping("findMenuInfo")
    public Message findMenuInfo() {
        return menuService.findMenuInfo();
    }

    //获取所有一级菜单
    //findOneMenuInfo
    @GetMapping("findOneMenuInfo")
    public Message findOneMenuInfo() {
        return menuService.findMenuOneInfo();
    }

    //获取
    @GetMapping("get/{id}")
    public Message get(@PathVariable Long id) {
        return menuService.getById(id);
    }

    //新增
    @PostMapping("save")
    public Message save(@RequestBody Menu menu) {
        return menuService.save(menu);
    }

    //修改
    @PostMapping("update")
    public Message updateById(@RequestBody Menu menu) {
        return menuService.updateById(menu);
    }

    //删除
    @PostMapping("remove/{id}")
    public Message remove(@PathVariable Long id) {
        return menuService.removeById(id);
    }

    //根据id列表删除
    @PostMapping("batchRemove")
    public Message batchRemove(@RequestBody List<Long> idList) {
        return menuService.removeByIds(idList);
    }

}
