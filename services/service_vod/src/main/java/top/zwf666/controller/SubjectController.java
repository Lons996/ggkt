package top.zwf666.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
//@CrossOrigin
@RequestMapping("/vod/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //获取指定的节点数据
    @GetMapping("/getChildSubject/{id}")
    public Message getChildSubject(@PathVariable Integer id) {
        if (id >= 0) {
            return subjectService.selectByParentId(id);
        }
        Message message = new Message();
        message.add("beans", "", 555).setMsg("服务异常");
        return message;
    }

    //导出数据
    @GetMapping("/getExcelData")
    public void getExcelData(HttpServletResponse response) {
        subjectService.getExcelData(response);
    }

    //下载模板
    @GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response, HttpServletRequest request) {
        subjectService.downloadExcel(response, request);
    }

    //上传excel数据
    @PostMapping("/uploadExcel")
    public Message uploadExcel(@RequestParam("file") MultipartFile excelFile) {
        Message message = new Message();
        if (excelFile == null) {
            message.add("", "", 555).setMsg("文件为空");
            return message;
        }

        //获取文件名称
        String filename = excelFile.getOriginalFilename();
        //获取文件后缀
        assert filename != null;
        String suffix = filename.substring(filename.lastIndexOf("."));
        //判断文件格式
        if (".xls".equals(suffix) || ".xlsx".equals(suffix)) {
            return subjectService.uploadExcel(excelFile);
        } else {
            message.add("", "", 555).setMsg("文件格式错误");
        }
        return message;
    }

}
