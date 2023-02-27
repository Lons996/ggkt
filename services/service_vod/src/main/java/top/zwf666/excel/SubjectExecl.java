package top.zwf666.excel;

import com.alibaba.excel.annotation.ExcelProperty;

public class SubjectExecl {

    //@ExcelProperty(value = "用户ID",index = 0):
    // 写入数据时设置表头名称为 “用户ID”,读取数据时会以index指定的下标优先，不指定则去寻找value指定的表头行
    @ExcelProperty(value = "用户ID")
    private Integer id;

    @ExcelProperty(value = "用户名称")
    private String name;

    @ExcelProperty(value = "用户年龄")
    private Integer age;

    @Override
    public String toString() {
        return "SubjectExecl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
