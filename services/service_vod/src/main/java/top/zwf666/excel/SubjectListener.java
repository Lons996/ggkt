package top.zwf666.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import top.zwf666.ggkt.vo.vod.SubjectEeVo;
import top.zwf666.mapper.SubjectMapper;
import top.zwf666.mycreatebean.exception.MyException;
import top.zwf666.mycreatebean.vod_entity.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {

    @Autowired
    private SubjectMapper subjectMapper;

    //保存数据
    private List<SubjectEeVo> list = new ArrayList<>();

    @Override
    public void invoke(SubjectEeVo data, AnalysisContext context) {
        //数据不为空便
        if (data != null) list.add(data);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        try {
            if(list.isEmpty()){
                throw new MyException("表格数据为空");
            }else {
                //将数据封装为实体类
                List<Subject> subjects = new ArrayList<>();
                list.forEach(item->{
                    Subject subject = new Subject();
                    BeanUtils.copyProperties(item,subject);
                    subjects.add(subject);
                });

                //添加数据
                int i = subjectMapper.insertBatch(subjects);
            }
            list = new ArrayList<>();
        } catch (Exception e) {
            throw new MyException(e);
        }
    }

}
