package top.zwf666.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zwf666.ggkt.vo.wechat.MenuVo;
import top.zwf666.mapper.MenuMapper;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.wechat_entity.Menu;
import top.zwf666.service.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Message findMenuOneInfo() {
        Message message = new Message();
        List<Menu> menuList = menuMapper.selectOnefindMenu();
        return message.add("menuList", menuList);
    }

    @Override
    public Message  findMenuInfo() {
        Message message = new Message();

        //封装最后菜单结果
        List<MenuVo> menuVoList = new ArrayList<>();

        //查询所有菜单(包含一，二级菜单)
        List<Menu> menuList = menuMapper.selectAll();

        //查询所有一级菜单
        List<Menu> oneMenuList = menuList.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());

        //将一级菜单封装最后list集合中
        for (Menu m : oneMenuList) {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(m, menuVo);
            menuVoList.add(menuVo);
        }
        //封装二级菜单数据（判断一级菜单id和二级菜单parent_id是否相同）
        for (MenuVo vo : menuVoList) {
            for (Menu menu : menuList) {
                if (vo.getId().equals(menu.getParentId())) {
                    MenuVo menuVo = new MenuVo();
                    BeanUtils.copyProperties(menu, menuVo);
                    vo.getChildren().add(menuVo);
                }
            }
        }
        return message.add("menuVoList", menuVoList, 200);
    }

    @Override
    public Message getById(Long id) {
        Message message = new Message();
        Menu menu = menuMapper.selectByPrimaryKey(id);
        return message.add("menu", menu, 200);
    }

    @Override
    public Message save(Menu menu) {
        Message message = new Message();


        return message;
    }

    @Override
    public Message removeById(Long id) {
        Message message = new Message();
        int i = menuMapper.deleteByPrimaryKey(id);
        return message.add("", "", 200);
    }

    @Override
    public Message removeByIds(List<Long> idList) {
        Message message = new Message();
        menuMapper.delListById(idList);
        return message.add("", "", 200);
    }

    @Override
    public Message updateById(Menu menu) {
        Message message = new Message();


        return message;
    }
}
