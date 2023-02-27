package top.zwf666.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import top.zwf666.ggkt.vo.vod.TeacherQueryVo;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.vod_entity.Teacher;

import java.util.List;

public interface TeacherService {
    int insert(Teacher teacher);

    boolean insertImg(MultipartFile file);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Teacher teacher);

    List<Teacher> selectAll();

    int deleteByPrimaryKey(long id);

    //条件分页查询
    List<Teacher> findQueryPage(TeacherQueryVo teacherQueryVo);

    //查询教师名称是否存在
    boolean selectTeacherName(String name, @Nullable Long id);

    //保存头像图片路径
    int saveAvatar(@Param("path") String path, @Param("id") Integer id);

    //批量删除
    int delBatchById(@Param("arr") Integer[] arr);

}
