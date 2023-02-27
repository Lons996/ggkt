package top.zwf666.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zwf666.ggkt.vo.vod.TeacherQueryVo;
import top.zwf666.mycreatebean.vod_entity.Teacher;

@Mapper
public interface TeacherMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher record);

    //条件分页查询
    List<Teacher> findQueryPage(TeacherQueryVo teacherQueryVo);
    //查询教师名称是否存在
    int selectTeacherName(@Param("name") String name,@Param("id") Long id);

    //保存头像图片路径
    int saveAvatar(@Param("path") String path,@Param("id") Integer id);
    //批量删除
    int delBatchById(@Param("arr") Integer[] arr);
    //根据ID查询名称 selectNameByPrimaryKey
    String selectNameByPrimaryKey(@Param("id") Long id);

}