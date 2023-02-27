package top.zwf666.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zwf666.ggkt.vo.vod.TeacherQueryVo;
import top.zwf666.mycreatebean.exception.MyException;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.vod_entity.Teacher;
import top.zwf666.service.TeacherService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/vod/teacher")
//@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Value("${server.port}")
    private String port;

    @Value("${ippath}")
    private String ip;

    @RequestMapping("/insert")
    public Message insert(@RequestBody Teacher teacher, HttpSession session) {
        Message message = new Message();
        if (StringUtils.isEmpty(teacher.getName())) {
            message.add("", "", 555).setMsg("教师名称为空");
        } else if (teacherService.selectTeacherName(teacher.getName(), null)) {
            message.add("", "", 555).setMsg("教师名称已存在");
        } else if (StringUtils.isEmpty(teacher.getIntro())) {
            message.add("", "", 555).setMsg("教师简介为空");
        } else if (StringUtils.isEmpty(teacher.getCareer())) {
            message.add("", "", 555).setMsg("教师职业生涯为空");
        } else if (teacher.getLevel() == null) {
            message.add("", "", 555).setMsg("排序为空");
        } else if (StringUtils.isEmpty(teacher.getJoinDate())) {
            message.add("", "", 555).setMsg("入驻时间为空");
        } else {
            //手动设置状态为 0 （未逻辑删除）
            if (teacher.getIsDeleted() == null) teacher.setIsDeleted((byte) 0);
            int insert = teacherService.insert(teacher);
            if (insert == 0) {
                message.add("id", insert, 444).setMsg("添加信息失败");
            } else {
                message.add("id", insert, 200).setMsg("添加信息成功");
                //将新增的id保存到session中
                session.getServletContext().setAttribute("teacherId", teacher.getId());
            }
        }
        return message;
    }

    //图片上传
    @RequestMapping("/insertImg")
    public Message insertImg(@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {
        Message message = new Message();

        if (file == null) {
            message.add("1", 1, 555).setMsg("文件为空");
        } else {
            String fileName = file.getOriginalFilename();
            //截取文件后缀名
            assert fileName != null;
            String suffix = fileName.substring(fileName.lastIndexOf("."));

            //拼接新的文件名称
            String newFileName = UUID.randomUUID().toString() + suffix;

            try {
                //获取 resource 路径
                String path = new ClassPathResource("static/avatar/", TeacherController.class.getClassLoader()).getURI().getPath();

                //创建File对象
                File toFile = new File(path + newFileName);
                if (toFile.exists()) {
                    toFile.mkdirs();
                }

                file.transferTo(toFile);

                //将头像图片路径保存到数据库
                String avatar = "http://" + ip + ":" + port + "/avatar/" + newFileName;
                //获取新增的id
                Object teacherId = session.getServletContext().getAttribute("teacherId");
                Integer integer = Integer.parseInt(teacherId.toString());
                System.out.println("teacherId = " + teacherId);
                System.out.println("integer = " + integer);
                int i = teacherService.saveAvatar(avatar, integer);

                message.add("id", i, 200).setMsg("文件上传成功");
            } catch (Exception e) {
                throw new MyException(e);
            }
        }
        return message;
    }

    @RequestMapping("/selectPrimaryKey")
    public Message selectPrimaryKey(Long id) {
        Message message = new Message();
        Teacher teacher = teacherService.selectByPrimaryKey(id);
        if (teacher == null) {
            message.add("bean", "", 404).setMsg("失败");
        } else {
            message.add("bean", teacher, 200).setMsg("成功");
        }
        return message;
    }

    @RequestMapping("/updateByPrimaryKey")
    public Message updateByPrimaryKey(@RequestBody Teacher teacher, HttpSession session) {
        Message message = new Message();
        if (StringUtils.isEmpty(teacher.getName())) {
            message.add("", "", 555).setMsg("教师名称为空");
        } else if (teacherService.selectTeacherName(teacher.getName(), teacher.getId())) {
            message.add("", "", 555).setMsg("教师名称已存在");
        } else if (StringUtils.isEmpty(teacher.getIntro())) {
            message.add("", "", 555).setMsg("教师简介为空");
        } else if (StringUtils.isEmpty(teacher.getCareer())) {
            message.add("", "", 555).setMsg("教师职业生涯为空");
        } else if (teacher.getLevel() == null) {
            message.add("", "", 555).setMsg("排序为空");
        } else if (StringUtils.isEmpty(teacher.getJoinDate())) {
            message.add("", "", 555).setMsg("入驻时间为空");
        } else {
            int i = teacherService.updateByPrimaryKey(teacher);
            //将修改的id保存到session中
            session.getServletContext().setAttribute("teacherId", teacher.getId());
            message.add("i", i, 200).setMsg("修改成功");
        }
        return message;
    }

    @RequestMapping("/selectAll")
    public Message selectAll() {
        Message message = new Message();
        List<Teacher> teachers = teacherService.selectAll();
        message.add("beans", teachers).setMsg("成功");
        return message;
    }

    @RequestMapping("/deleteByPrimaryKey")
    public Message deleteByPrimaryKey(Long id) {
        System.out.println("id = " + id);
        Message message = new Message();
        int i = teacherService.deleteByPrimaryKey(id);
        message.add("beans", i, 200).setMsg("删除成功");
        return message;
    }

    /**
     * 条件查询分页
     *
     * @param pageNum  当前页
     * @param pageSize 每也显示条数
     */
    @PostMapping("/findQueryPage/{pageNum}/{pageSize}")
    public Message findQueryPage(@PathVariable("pageNum") int pageNum,
                                 @PathVariable("pageSize") int pageSize,
                                 @RequestBody(required = false) TeacherQueryVo teacherQueryVo
    ) {
        Message message = new Message();

        //分页：在查询之前传入页码及每页数
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> queryPage = teacherService.findQueryPage(teacherQueryVo);

        //使用pageInfo 包装查询后的结果 (封装了详细的分页信息,连续显示的页数(页码))
        PageInfo<Teacher> empPageInfo = new PageInfo<>(queryPage, 5);

        if (queryPage.size() > 0) {
            message.add("data", empPageInfo, 200).setMsg("成功");
        } else {
            message.add("", "", 555).setMsg("无数据");
        }
        return message;
    }

    //批量删除
    @PostMapping("/delBatchById")
    public Message delBatchById(@RequestBody Map<String, Integer[]> arr) {
        Message message = new Message();
        Integer[] arrs = arr.get("arr");
        if (arr.isEmpty()) {
            message.add("status", "", 200).setMsg("服务异常:无数据");
        } else if (arrs == null || arrs.length <= 0) {
            message.add("status", "", 200).setMsg("服务异常");
        } else {
            int i = teacherService.delBatchById(arrs);
            message.add("status", i, 200).setMsg("删除成功");
        }
        return message;
    }


}
