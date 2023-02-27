package top.zwf666.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zwf666.mapper.UserInfoMapper;
import top.zwf666.mycreatebean.user_entity.UserInfo;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Message getUser(Long userId) {
        Message m = new Message();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        m.add("bean", userInfo, 200);
        return m;
    }
}
