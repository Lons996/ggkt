package top.zwf666.mapper;

import java.util.List;
import top.zwf666.mycreatebean.vod_entity.CourseCollect;

public interface CourseCollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseCollect record);

    CourseCollect selectByPrimaryKey(Long id);

    List<CourseCollect> selectAll();

    int updateByPrimaryKey(CourseCollect record);
}