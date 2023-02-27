package top.zwf666.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zwf666.mycreatebean.vod_entity.Subject;

import javax.sound.sampled.BooleanControl;

@Mapper
public interface SubjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Subject record);

    Subject selectByPrimaryKey(Long id);

    List<Subject> selectAll();

    int updateByPrimaryKey(Subject record);

    //查询指定父级id的层级数据
    List<Subject> selectByParentId(@Param("parentId") Integer parentId);

    //查询指定的id下是否存在子级节点
    int selectIsNode(@Param("id") Long id);

    //批量插入数据
    int insertBatch(@Param("list") List<Subject> id);


}