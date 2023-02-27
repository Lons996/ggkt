package top.zwf666.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.zwf666.ggkt.vo.vod.TeacherQueryVo;
import top.zwf666.mapper.TeacherMapper;
import top.zwf666.mycreatebean.vod_entity.Teacher;
import top.zwf666.service.TeacherService;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public int insert(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    @Override
    public boolean insertImg(MultipartFile file) {
        System.out.println("file = " + file);
        return false;
    }

    @Override
    public Teacher selectByPrimaryKey(Long id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Teacher teacher) {
        return teacherMapper.updateByPrimaryKey(teacher);
    }

    @Override
    public List<Teacher> selectAll() {
        return teacherMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(long id) {
        return teacherMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Teacher> findQueryPage(TeacherQueryVo teacherQueryVo) {
        return teacherMapper.findQueryPage(teacherQueryVo);
    }

    @Override
    public boolean selectTeacherName(String name,@Nullable Long id) {
        int i = teacherMapper.selectTeacherName(name,id);
        System.out.println("id = " + id);
        return i > 0;
    }

    @Override
    public int saveAvatar(String path,Integer id) {
        return teacherMapper.saveAvatar(path,id);
    }

    @Override
    public int delBatchById(Integer[] arr) {
        return teacherMapper.delBatchById(arr);
    }




}
