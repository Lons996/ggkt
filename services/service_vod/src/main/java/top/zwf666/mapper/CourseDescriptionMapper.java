package top.zwf666.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zwf666.mycreatebean.vod_entity.CourseDescription;

@Mapper
public interface CourseDescriptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CourseDescription record);

    CourseDescription selectByPrimaryKey(Long id);

    List<CourseDescription> selectAll();

    int updateByPrimaryKey(CourseDescription record);

    //根据课程id查询课程描述表
    CourseDescription selectCourseDescriptionByCourseId(@Param("id") Long courseId);

    //根据课程id修改课程描述表信息
    void updateCourseDescription(@Param("id") Long id, @Param("description") String description);
}