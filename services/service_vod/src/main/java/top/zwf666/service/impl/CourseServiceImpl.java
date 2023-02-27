package top.zwf666.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import top.zwf666.ggkt.vo.vod.CourseFormVo;
import top.zwf666.ggkt.vo.vod.CoursePublishVo;
import top.zwf666.ggkt.vo.vod.CourseQueryVo;
import top.zwf666.mapper.CourseDescriptionMapper;
import top.zwf666.mapper.CourseMapper;
import top.zwf666.mapper.VideoMapper;
import top.zwf666.mycreatebean.exception.MyException;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.vod_entity.Course;
import top.zwf666.mycreatebean.vod_entity.CourseDescription;
import top.zwf666.mycreatebean.vod_entity.Video;
import top.zwf666.service.ChapterService;
import top.zwf666.service.CourseService;
import top.zwf666.service.VideoService;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoMapper videoMapper;

    @Value("${ippath}")
    private String ip;

    @Value("${server.port}")
    private String port;

    @Override
    public Message getCourse(Integer pageNum, Integer pageSize, CourseQueryVo courseQueryVo) {
        Message message = new Message();
        System.out.println("courseQueryVo = " + courseQueryVo);
        //查询数据分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<Course> pageData = courseMapper.getPageData(courseQueryVo);
        System.out.println("pageData.size() = " + pageData.size());

        //使用pageInfo 包装查询后的结果 (封装了详细的分页信息,连续显示的页数(页码))
        PageInfo<Course> pageInfo = new PageInfo<>(pageData, 5);
        message.add("PageList", pageInfo, 200).setMsg("success");
        return message;
    }

    @Override
    public Long insertCourse(CourseFormVo form) {
        //将Vo对象中的属性copy到实体类中
        Course course = new Course();
        BeanUtils.copyProperties(form, course);
        course.setPublishTime(new Date()); //发布时间
        course.setBuyCount(0L);//购买的人数
        course.setStatus((byte) 0);//状态为发布
        course.setViewCount(0L);//观看人数
        course.setCover("");//封面图片路径

        int insert = courseMapper.insert(course);
        return course.getId();
    }

    @Override
    public Long insertCourseDescription(String description, Long courseId) {
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setCourseId(courseId);//课程信息新增Id
        courseDescription.setDescription(description);//课程描述
        int insert = courseDescriptionMapper.insert(courseDescription);
        return courseDescription.getId();
    }

    @Override
    public Message saveImg(Long newCourseId, MultipartFile img) {
        Message message = new Message();
        try {
            //获取static文件路径（文件夹必须存在）
            String path = Thread.currentThread().getClass().getResource("/static").getPath();
            //获取文件名称
            String filename = img.getOriginalFilename();
            //获取文件后缀
            assert filename != null;
            String suffix = filename.substring(filename.lastIndexOf("."));

            //拼接文件新名称
            filename = UUID.randomUUID().toString() + suffix;

            //拼接完整保存路径
            File file = new File(path + "/avatar/" + filename);

            //文件夹不存在便创建
            boolean exists = file.exists();
            if (!file.exists()) {
                file.mkdirs();
            }
            //更新数据库中封面图片的路径
            String url = "http://" + ip + ":" + port + "/avatar/" + filename;
            System.out.println("url = " + url);

            Long aLong = courseMapper.updateCoverById(newCourseId, url);

            //保存图片
            img.transferTo(file);
            if (aLong > 0) {
                message.add("", "", 200).setMsg("图片上传成功");
            } else message.add("", "", 555).setMsg("图片上传失败");
        } catch (Exception e) {
            throw new MyException(e);
        }
        return message;
    }

    @Override
    public Message updateData(Long id) {
        Message message = new Message();

        //根据id查询课程表（course）数据
        Course course = courseMapper.selectByPrimaryKey(id);

        //根据id课程描述表（course_description）数据
        CourseDescription courseDescription = courseDescriptionMapper.selectCourseDescriptionByCourseId(id);

        message.add("course", course).add("courseDescription", courseDescription, 200).setMsg("success");

        return message;
    }

    @Override
    public Message updateCourse(CourseFormVo form) {
        Message message = new Message();
        //修改课程表信息
        courseMapper.updateCourse(form);
        //修改课程描述表信息
        courseDescriptionMapper.updateCourseDescription(form.getId(), form.getDescription());

        message.add("", "", 200).setMsg("修改成功！");
        return message;
    }

    @Override
    public Message removeVideo(Long id) {
        Message m = new Message();

        //根据课程id删除小节下的作用课时视频
        videoService.removeVideoFile(id);

        //根据课程id删除小节
        videoService.removeVideoByCourseId(id);

        //根据课程id删除章节
        chapterService.removeChapterByCourseId(id);

        //根据课程id删除章节描述
        chapterService.removeChapterByCourseId(id);

        //根据课程id删除课程
        courseMapper.deleteByPrimaryKey(id);
        return m.add("", "", 200);
    }

    @Override
    public Message backfillVideoData(Integer videoId) {
        Message message = new Message();
        if (StringUtils.isEmpty(videoId)) {
            message.add("", "", 555).setMsg("课时id为空！");
        }
        Video video = videoMapper.backfillVideoData(videoId);

        message.add("data", video, 200).setMsg("查询成功");
        return message;
    }

    @Override
    public Message backfillCourseData(Integer id) {
        Message m = new Message();
        CoursePublishVo coursePublishVo = courseMapper.backfillCourseData(id);
        return m.add("data", coursePublishVo, 200);
    }

    @Override
    public Message publishCourse(Integer courseId) {
        Message m = new Message();
        courseMapper.publishCourse(courseId);
        m.add("", "", 200).setMsg("成功发布");
        return m;
    }

    @Override
    public Message isPublish(Integer courseId) {
        Message m = new Message();
        int num = courseMapper.isPublish(courseId);
        boolean b = num != 0;
        return m.add("isPublish", b, 200);
    }
}

