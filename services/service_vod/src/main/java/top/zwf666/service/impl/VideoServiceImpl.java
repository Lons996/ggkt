package top.zwf666.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import top.zwf666.mapper.VideoMapper;
import top.zwf666.mycreatebean.exception.MyException;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.vod_entity.Video;
import top.zwf666.service.VideoService;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public int removeVideoByCourseId(Long id) {
        return videoMapper.deleteVideoByPrimaryKey(id);
    }

    @Override
    public void removeVideoFile(Long id) {
        try {
            //根据课程id查询所有视频文件名称
            List<String> list = videoMapper.selectVideoOriginalNameById(id);
            if (list.isEmpty()) return;

            //获取文件路径
            String path = new ClassPathResource("static", Thread.currentThread().getContextClassLoader()).getURL().getPath();

            //删除文件
            list.forEach(item -> {
                File file = new File(path + "/video/" + item);
                if (file.exists()) {
                    file.delete();
                }
            });
        } catch (Exception e) {
            throw new MyException(e);
        }
    }

    @Override
    public Message selectAll(Map<String, Integer[]> arr) {
        Message message = new Message();
        Integer[] arrs = arr.get("arr");
        List<Video> videos = videoMapper.selectAllByArray(arrs);
        return message.add("list", videos, 200);
    }
}
