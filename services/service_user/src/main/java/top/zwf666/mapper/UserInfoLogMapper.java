package top.zwf666.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import top.zwf666.mycreatebean.user_entity.UserInfoLog;

@Mapper
public interface UserInfoLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfoLog record);

    UserInfoLog selectByPrimaryKey(Long id);

    List<UserInfoLog> selectAll();

    int updateByPrimaryKey(UserInfoLog record);
}