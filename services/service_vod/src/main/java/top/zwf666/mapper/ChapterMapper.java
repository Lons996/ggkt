package top.zwf666.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zwf666.mycreatebean.vod_entity.Chapter;
import top.zwf666.mycreatebean.vod_entity.Video;

import java.util.List;

@Mapper
public interface ChapterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Chapter record);

    Chapter selectByPrimaryKey(Long id);

    List<Chapter> selectAll();

    int updateByPrimaryKey(Chapter record);

    int deleteByByCourseId(Long id);

    String selectVideoOriginalName(@Param("id") Integer id);

    List<Chapter> backfillChapter(@Param("id") Integer id);
}