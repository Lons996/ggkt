package top.zwf666.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import top.zwf666.mapper.ChapterMapper;
import top.zwf666.mapper.VideoMapper;
import top.zwf666.mycreatebean.exception.MyException;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.vod_entity.Chapter;
import top.zwf666.mycreatebean.vod_entity.Video;
import top.zwf666.service.ChapterService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private VideoMapper videoMapper;

    private static String saveFile(MultipartFile file) {
        //获取文件名称
        String filename = file.getOriginalFilename();
        //获取文件后缀
        assert filename != null;
        String suffix = filename.substring(filename.lastIndexOf("."));

        //拼接保存的文件名称
        String newName = UUID.randomUUID().toString() + suffix;
        try {
            //获取保存路径
            String path = Thread.currentThread().getContextClassLoader().getResource("static").getPath();
            //创建File对象
            File toFile = new File(path + "/video");
            if (!toFile.exists()) {
                toFile.mkdirs();
            }
            //保存
            file.transferTo(new File(toFile.getPath() + "/" + newName));
            return newName;
        } catch (IOException e) {
            throw new MyException(e);
        }
    }

    @Override
    public Message updateChapter(Chapter chapter) {
        Message message = new Message();
        int i = chapterMapper.updateByPrimaryKey(chapter);
        if (i > 0) {
            message.add("", "", 200).setMsg("修改成功");
        } else {
            message.add("", "", 555).setMsg("修改失败");
        }
        return message;
    }

    @Override
    public Message insertChart(Chapter chapter) {
        Message message = new Message();
        //添加新章节并返回新增ID
        int insert = chapterMapper.insert(chapter);
        if (insert <= 0) message.add("", "", 500).setMsg("新增失败");
        message.add("id", chapter.getId(), 200).setMsg("新增章节成功");
        return message;
    }

    @Override
    public Message delChapterById(Long id) {
        Message message = new Message();
        chapterMapper.deleteByPrimaryKey(id);
        message.add("", "", 200).setMsg("删除成功");
        return message;
    }

    @Override
    public Message insertVideo(MultipartFile file, Video video) {
        Message message = new Message();
        if (StringUtils.isEmpty(video.getCourseId())) {
            message.add("", "", 556).setMsg("课程id为空！");
        } else if (StringUtils.isEmpty(video.getChapterId())) {
            message.add("", "", 556).setMsg("章节id为空！");
        } else if (StringUtils.isEmpty(video.getTitle())) {
            message.add("", "", 556).setMsg("视频标题为空！");
        } else if (StringUtils.isEmpty(video.getSort())) {
            video.setSort(1);
        } else {
            //保存视频(true = 成功)
            String suffix = null;
            if (file != null) {
                suffix = saveFile(file);
            }

            //保存课时信息
            video.setVideoOriginalName(suffix);
            int i = videoMapper.insertVideo(video);

            message.add("videoId", video.getId(), 200).setMsg("添加成功");
        }
        return message;
    }

    @Override
    public int removeChapterByCourseId(Long id) {
        return chapterMapper.deleteByByCourseId(id);
    }

    @Override
    public Message updateVideoById(Integer id, MultipartFile file, Video video) {
        Message m = new Message();
        if (StringUtils.isEmpty(id)) {
            m.add("", "", 555).setMsg("课时id为空");
        } else if (StringUtils.isEmpty(video.getTitle())) {
            m.add("", "", 555).setMsg("课时标题为空");
        } else if (StringUtils.isEmpty(video.getSort())) {
            video.setSort(0);
        } else {
            if (file != null) {
                //根据课时id查询视频文件名称
                String videoOriginalName = chapterMapper.selectVideoOriginalName(id);
                System.out.println(videoOriginalName);
                //根据文件名称删除视频文件
                if (!StringUtils.isEmpty(videoOriginalName)) {
                    deleteFileByName(videoOriginalName);
                }
                //保存新视频文件并返回新的文件名
                String newFileName = saveFile(file);
                video.setVideoOriginalName(newFileName);
            }
            //保存修改信息
            videoMapper.updateVideoById(video);

            //查询编辑
            m.add("", "", 200).setMsg("编辑成功");
        }
        return m;
    }

    @Override
    public Message backfillChapter(Integer id) {
        Message m = new Message();
        if (StringUtils.isEmpty(id)) {
            m.add("", "", 555).setMsg("课程id为空！");
        } else {
            List<Chapter> list = chapterMapper.backfillChapter(id);
            m.add("data", list, 200);
        }
        return m;
    }

    @Override
    public Message selectVideoByChapterId(Integer id) {
        Message m = new Message();
        if (StringUtils.isEmpty(id)) {
            m.add("", "", 555).setMsg("章节id为空！");
        } else {
            List<Video> list = videoMapper.selectVideoByChapterId(id);
            m.add("data", list, 200);
        }
        return m;
    }

    //根据名称删除视频文件
    public Boolean deleteFileByName(String videoOriginalName) {
        try {
            //获取文件路径
            String path = new ClassPathResource("static", Thread.currentThread().getContextClassLoader()).getURL().getPath();

            //拼接最终路径
            File file = new File(path + "/video/" + videoOriginalName);

            //删除文件
            return file.delete();
        } catch (Exception e) {
            throw new MyException("删除视频文件异常");
        }
    }
}
