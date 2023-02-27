package top.zwf666.service.impl;

import com.alibaba.excel.EasyExcel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.zwf666.excel.ExcelListener;
import top.zwf666.excel.SubjectListener;
import top.zwf666.ggkt.vo.vod.SubjectEeVo;
import top.zwf666.mapper.SubjectMapper;
import top.zwf666.mycreatebean.exception.MyException;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.vod_entity.Subject;
import top.zwf666.service.SubjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private SubjectListener subjectListener;

    @Override
    public Message selectByParentId(Integer parentId) {
        Message message = new Message();

        //查询指定父级id的层级数据
        List<Subject> subjects = subjectMapper.selectByParentId(parentId);

        //查询每个id下的是否存在子级节点
        for (Subject subject : subjects) {
            int i = subjectMapper.selectIsNode(subject.getId());
            //设置 hasChildren属性值，标识是否存在子级节点
            subject.setHasChildren(i > 0);
        }
        if (subjects.isEmpty()) {
            message.add("beans", "", 555).setMsg("无数据");
        } else {
            message.add("beans", subjects, 200).setMsg("success");
        }
        return message;
    }

    @Override
    public void getExcelData(HttpServletResponse response) {
        try {
            //设置响应的 mime 类型
            response.setContentType("application/vnd.ms-excel");
            //设置服务端响应的 字节码类型
            response.setCharacterEncoding("utf-8");
            //设置请求头：以下载方式打开，并指定返回的文件名称
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

            //查询数据:并将数据中的需要的信息封装为另一个实体类模板中
            List<Subject> subjects = subjectMapper.selectAll();
            List<SubjectEeVo> subjectEeVos = new ArrayList<>(); //实体类模板
            subjects.forEach(item -> {
                SubjectEeVo subjectVo = new SubjectEeVo();
                BeanUtils.copyProperties(item, subjectVo);
                subjectEeVos.add(subjectVo);
            });

            //将封装的实体类模板集合写入文件，并返回
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet(fileName).doWrite(subjectEeVos);
        } catch (Exception e) {
            throw new MyException(e);
        }
    }

    @Override
    public void downloadExcel(HttpServletResponse response, HttpServletRequest request) {
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {

            //设置响应的 mime 类型
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //设置请求头：以下载方式打开，并指定返回的文件名称
            String fileName = URLEncoder.encode("subject.xlsx", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            //设置服务端响应的 字节码类型
            response.setCharacterEncoding("UTF-8");
            //获取文件路径
            String path = new ClassPathResource("static/excel/" + fileName, Subject.class.getClassLoader()).getURL().getPath();
            System.out.println("path = " + path);

            //获取输入,输出流
            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(response.getOutputStream());

            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, length);
                bos.flush();
            }
        } catch (Exception e) {
            throw new MyException(e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public Message uploadExcel(MultipartFile excelFile) {
        Message message = new Message();
        try {
            EasyExcel.read(excelFile.getInputStream(), SubjectEeVo.class, subjectListener).sheet().doRead();
            message.add("","",200).setMsg("上传成功");
            return message;
        } catch (IOException e) {
            throw new MyException(e);
        }
    }

}
