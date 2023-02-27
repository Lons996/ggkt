package top.zwf666.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zwf666.ggkt.vo.vod.CourseFormVo;
import top.zwf666.ggkt.vo.vod.CourseQueryVo;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.vod_entity.Chapter;
import top.zwf666.mycreatebean.vod_entity.Video;
import top.zwf666.service.ChapterService;
import top.zwf666.service.CourseService;
import top.zwf666.service.VideoService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/vod/course")
//@CrossOrigin
public class CourseController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;


    //获取课程的列表信息 == 条件查询
    @PostMapping("/getCourse/{pageNum}/{pageSize}")
    public Message getCourse(@PathVariable Integer pageNum,
                             @PathVariable Integer pageSize,
                             @RequestBody(required = false) CourseQueryVo courseQueryVo
    ) {
        return courseService.getCourse(pageNum, pageSize, courseQueryVo);
    }

    @Transactional
    //保存课程信息
    @PostMapping("/saveCourseData")
    public Message saveCourseData(CourseFormVo form, @RequestParam(value = "img", required = false) MultipartFile img, HttpServletRequest request) {
        Message message = new Message();
        if (StringUtils.isEmpty(form.getTitle())) {
            message.add("", "", 555).setMsg("课程标题为空!");
        } else if (StringUtils.isEmpty(form.getDescription())) {
            message.add("", "", 555).setMsg("课程描述为空!");
        } else if (form.getSubjectId() <= 0 || form.getSubjectParentId() <= 0) {
            message.add("", "", 555).setMsg("课程类别为空!");
        } else if (form.getPrice() < 0 || StringUtils.isEmpty(form.getPrice())) {
            message.add("", "", 555).setMsg("课程价格错误!");
        } else if (form.getTeacherId() <= 0 || StringUtils.isEmpty(form.getTeacherId())) {
            message.add("", "", 555).setMsg("课程教师为空!");
        } else if (form.getLessonNum() <= 0) {
            message.add("", "", 555).setMsg("总课时为空!");
        } else {
            //添加课程表(course)信息并返回新增ID
            Long newCourseId = courseService.insertCourse(form);
            //添加课程描述表（course_description）信息并返回新增ID
            Long newCourseCourseDescriptionId = courseService.insertCourseDescription(form.getDescription(), newCourseId);
            //保存图片文件
            if (img == null) {
                message.add("id", newCourseId, 200).setMsg("信息添加成功");
            } else {
                Message msg = courseService.saveImg(newCourseId, img);
                if (msg.getCode() == 200) {
                    message.add("id", newCourseId, 200).setMsg("信息、图片添加成功");
                } else {
                    message.add("id", newCourseId, 200).setMsg("信息添加成功,图片异常");
                }
            }
        }
        return message;
    }

    @Transactional
    //课程信息：保存修改
    @PostMapping("/saveUpdateCourseData")
    public Message saveUpdateCourseData(CourseFormVo form, @RequestParam(value = "img", required = false) MultipartFile img) {
        Message message = new Message();
        if (StringUtils.isEmpty(form.getId())) {
            message.add("", "", 556).setMsg("课程id参数为空!");
        } else if (StringUtils.isEmpty(form.getTitle())) {
            message.add("", "", 555).setMsg("课程标题为空!");
        } else if (StringUtils.isEmpty(form.getDescription())) {
            message.add("", "", 555).setMsg("课程描述为空!");
        } else if (form.getSubjectId() <= 0 || form.getSubjectParentId() <= 0) {
            message.add("", "", 555).setMsg("课程类别为空!");
        } else if (form.getPrice() < 0 || StringUtils.isEmpty(form.getPrice())) {
            message.add("", "", 555).setMsg("课程价格错误!");
        } else if (form.getTeacherId() <= 0 || StringUtils.isEmpty(form.getTeacherId())) {
            message.add("", "", 555).setMsg("课程教师为空!");
        } else if (form.getLessonNum() <= 0) {
            message.add("", "", 555).setMsg("总课时为空!");
        } else {
            //根据课程id修改课程表(course)信息 和 课程描述表（course_description）信息
            message = courseService.updateCourse(form);
            //保存修改图片文件
            if (img != null) {
                Message messageImg = courseService.saveImg(form.getId(), img);
                if (messageImg.getCode() != 200) {
                    message.setMsg("基本信息修改成功，图片修改失败");
                }
            }
        }
        return message;
    }

    @Transactional
    //修改回填课程信息
    @PostMapping("/updateCourseData")
    public Message updateCourseData(@RequestParam("id") Long id) {
        System.out.println("id = " + id);
        Message message = new Message();
        if (StringUtils.isEmpty(id) || id <= 0) {
            message.add("", "", 555).setMsg("参数异常！");
        } else {
            return courseService.updateData(id);
        }
        return message;
    }

    //添加章节
    @PostMapping("/insertChart")
    public Message insertChart(Chapter chapter) {
        Message message = new Message();
        System.out.println("chapter = " + chapter);
        if ("".equals(chapter.getTitle())) {
            message.add("", "", 555).setMsg("标题为空");
        } else if (chapter.getCourseId() <= 0) {
            message.add("", "", 555).setMsg("课程ID为空");
        } else {
            //保存新增章节
            return chapterService.insertChart(chapter);
        }
        return message;
    }

    //修改章节名称
    @PostMapping("/updateChart")
    public Message updateChart(Chapter chapter) {
        Message message = new Message();
        if ("".equals(chapter.getTitle())) {
            message.add("", "", 555).setMsg("标题为空");
        } else if (chapter.getId() <= 0) {
            message.add("", "", 555).setMsg("课程ID为空");
        } else {
            return chapterService.updateChapter(chapter);
        }
        return message;
    }

    //删除章节
    @PostMapping("/delChapterById")
    public Message delChapterById(Long id) {
        Message message = new Message();
        if (StringUtils.isEmpty(id)) {
            message.add("", "", 555).setMsg("id没有了");
            return message;
        }
        return chapterService.delChapterById(id);
    }

    //添加章节视频（添加课时）
    @PostMapping("/insertVideo")
    public Message insertVideo(@RequestParam("video") @Nullable MultipartFile file, Video video) {
        Message message = new Message();
        return chapterService.insertVideo(file, video);
    }

    //修改课时信息
    @PostMapping("updateVideoById/{id}")
    public Message updateVideoById(@PathVariable Integer id, @RequestParam("video") @Nullable MultipartFile file, Video video) {
        return chapterService.updateVideoById(id, file, video);
    }

    //删除课程
    @GetMapping("removeVideo/{id}")
    @Transactional
    public Message removeVideo(@PathVariable Long id) {
        return courseService.removeVideo(id);
    }

    //查询课时信息
    @GetMapping("backfillVideoData/{videoId}")
    public Message backfillVideoData(@PathVariable Integer videoId) {
        return courseService.backfillVideoData(videoId);
    }

    //查询所有课时信息
    @PostMapping("selectAll")
    public Message selectAll(@RequestBody Map<String, Integer[]> arr) {
        return videoService.selectAll(arr);
    }

    //查询章节信息
    @GetMapping("backfillChapter/{id}")
    public Message backfillChapter(@PathVariable Integer id) {
        return chapterService.backfillChapter(id);
    }

    @GetMapping("selectVideoByChapterId/{id}")
    public Message selectVideoByChapterId(@PathVariable Integer id) {
        return chapterService.selectVideoByChapterId(id);
    }

    //查询最终发布课程页面的回显数据
    @GetMapping("backfillCourseData/{id}")
    public Message backfillCourseData(@PathVariable Integer id) {
        return courseService.backfillCourseData(id);
    }

    //发布课程
    @GetMapping("publishCourse/{courseId}")
    public Message publishCourse(@PathVariable Integer courseId) {
        return courseService.publishCourse(courseId);
    }

    //查询课程是否发布
    @GetMapping("isPublish/{courseId}")
    public Message isPublish(@PathVariable Integer courseId) {
        return courseService.isPublish(courseId);
    }
}
