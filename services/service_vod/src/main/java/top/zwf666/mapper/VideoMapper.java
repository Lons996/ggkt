package top.zwf666.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zwf666.mycreatebean.vod_entity.Video;

import java.util.List;

@Mapper
public interface VideoMapper {
    int deleteByPrimaryKey(Long id);

    int deleteVideoByPrimaryKey(Long id);

    int insert(Video record);

    Video selectByPrimaryKey(Long id);

    List<Video> selectAll();

    int updateByPrimaryKey(Video record);

    int insertVideo( Video video);

    Video backfillVideoData(@Param("videoId") Integer videoId);

    int updateVideoById(Video video);

    List<String> selectVideoOriginalNameById(@Param("id") Long id);

    List<Video> selectAllByArray(@Param("arrs") Integer[] arrs);

    List<Video> selectVideoByChapterId(@Param("id") Integer id);
}