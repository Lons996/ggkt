package top.zwf666.service;

import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.wechat_entity.Menu;

import java.util.List;

public interface MenuService {
    Message findMenuInfo();

    Message findMenuOneInfo();

    Message getById(Long id);

    Message save(Menu menu);

    Message removeById(Long id);

    Message removeByIds(List<Long> idList);

    Message updateById(Menu menu);
}
