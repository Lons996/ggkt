package top.zwf666.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zwf666.ggkt.vo.vod.VideoVisitorCountVo;
import top.zwf666.mycreatebean.vod_entity.VideoVisitor;

import java.util.List;

@Mapper
public interface VideoVisitorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VideoVisitor record);

    VideoVisitor selectByPrimaryKey(Long id);

    List<VideoVisitor> selectAll();

    int updateByPrimaryKey(VideoVisitor record);

    List<VideoVisitorCountVo> selectSortData(@Param("endDate") String endDate,
                                             @Param("startDate") String startDate,
                                             @Param("courseId") Long courseId);

}