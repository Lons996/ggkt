package top.zwf666.service;

import org.springframework.web.multipart.MultipartFile;
import top.zwf666.ggkt.vo.vod.CourseFormVo;
import top.zwf666.ggkt.vo.vod.CourseQueryVo;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.vod_entity.Video;

import java.io.IOException;
import java.util.Map;

public interface CourseService {

    Message getCourse(Integer pageNum, Integer pageSize, CourseQueryVo courseQueryVo);

    Long insertCourse(CourseFormVo form);

    Long insertCourseDescription(String description, Long courseId);

    Message saveImg(Long newCourseId, MultipartFile img);

    Message updateData(Long id);

    Message updateCourse(CourseFormVo form);

    Message removeVideo(Long id);

    Message backfillVideoData(Integer videoId);

    Message backfillCourseData(Integer id);

    Message publishCourse(Integer courseId);

    Message isPublish(Integer courseId);

}
