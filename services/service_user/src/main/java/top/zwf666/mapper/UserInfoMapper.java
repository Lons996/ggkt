package top.zwf666.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import top.zwf666.mycreatebean.user_entity.UserInfo;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}