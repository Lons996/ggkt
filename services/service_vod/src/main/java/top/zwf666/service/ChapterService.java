package top.zwf666.service;


import org.springframework.web.multipart.MultipartFile;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.vod_entity.Chapter;
import top.zwf666.mycreatebean.vod_entity.Video;

public interface ChapterService {

     Message updateChapter(Chapter chapter);

    Message insertChart(Chapter chapter);

    Message delChapterById(Long id);

    Message insertVideo(MultipartFile file, Video video);

    int removeChapterByCourseId(Long id);

    Message updateVideoById(Integer id, MultipartFile file, Video video);

    Message backfillChapter(Integer id);

    Message selectVideoByChapterId(Integer id);

}
