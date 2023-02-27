package Test;


import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zwf666.excel.ExcelListener;
import top.zwf666.excel.SubjectExecl;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class TeacherTest {


    //excel 导出数据
    @Test
    public void fun() {
        //准备数据
        List<SubjectExecl> data = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            SubjectExecl subjectExecl = new SubjectExecl();
            subjectExecl.setId(i);
            subjectExecl.setAge(20 + i);
            subjectExecl.setName("我不快乐吗" + i);
            data.add(subjectExecl);
        }

        //写入指定文件
        EasyExcel.write("C:/Users/星海/Desktop/app.xlsx", SubjectExecl.class).sheet("表格1").doWrite(data);
    }

    //excel导入数据
    @Test
    public void fun2() {
        //接收数据
        List<SubjectExecl> subjectExeclList = new ArrayList<>();

        //读取指定文件
        EasyExcel.read("C:/Users/星海/Desktop/app.xlsx", SubjectExecl.class, new ExcelListener()).sheet("表格1").doRead();
    }


    public void fun3(int a, String b) {
        System.out.println(a);
    }

    public void fun3(String a, int b) {
        System.out.println(a);
    }

    @Test
    void fun33(int a) {
        fun3(1,"str");
        fun3("str",1);
    }


}
