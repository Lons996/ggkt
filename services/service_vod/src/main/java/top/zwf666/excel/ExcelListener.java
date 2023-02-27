package top.zwf666.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ExcelListener extends AnalysisEventListener<SubjectExecl> {

    //获取表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        headMap.forEach((index,item)->{
            System.out.println("item = " + item);
        });
    }

    //一行一行读取数据，并封装成一个对象，为第一个参数返回
    @Override
    public void invoke(SubjectExecl subjectExecl, AnalysisContext analysisContext) {
        System.out.println("subjectExecl = " + subjectExecl);
    }

    //读取完成后执行的方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
