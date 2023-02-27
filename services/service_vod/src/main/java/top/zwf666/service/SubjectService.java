package top.zwf666.service;

import org.springframework.web.multipart.MultipartFile;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.mycreatebean.vod_entity.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SubjectService {
    //查询指定父级id的层级数据
    Message selectByParentId(Integer parentId);

    void getExcelData(HttpServletResponse response);

    void downloadExcel(HttpServletResponse response, HttpServletRequest request);

    Message uploadExcel(MultipartFile excelFile);
}
