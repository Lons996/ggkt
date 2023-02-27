package top.zwf666.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zwf666.ggkt.vo.vod.CourseFormVo;
import top.zwf666.ggkt.vo.vod.CoursePublishVo;
import top.zwf666.ggkt.vo.vod.CourseQueryVo;
import top.zwf666.mycreatebean.vod_entity.Course;

import java.util.List;

@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Course record);

    Course selectByPrimaryKey(Long id);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    //分页条件查询
    List<Course> getPageData(CourseQueryVo courseQueryVo);

    //根据ID查询名称
    String selectNameByPrimaryKey(@Param("id") Long id);

    Long updateCoverById(@Param("id") Long newCourseId,@Param("cover")  String url);

    void updateCourse(CourseFormVo form);

    CoursePublishVo backfillCourseData(@Param("id") Integer id);

    void publishCourse(@Param("id") Integer courseId);

    int isPublish(@Param("id") Integer courseId);
}