package top.zwf666.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.zwf666.ggkt.vo.vod.VideoVisitorCountVo;
import top.zwf666.mapper.VideoVisitorMapper;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.service.VideoVisitorService;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class VideoVisitorServiceImpl implements VideoVisitorService {
    @Autowired
    private VideoVisitorMapper videoVisitorMapper;

    @Override
    public Message getData(String endDate, String startDate, Long courseId) {
        Message message = new Message();
        if(StringUtils.isEmpty(courseId)){
            return message.add("","",500);
        }
        if("endDate".equals(endDate)){
            endDate = "";
        }
        if("startDate".equals(startDate)){
            startDate = "";
        }
        //数据
        List<VideoVisitorCountVo> videoVisitorCountVos = videoVisitorMapper.selectSortData(endDate, startDate, courseId);

        //创建两个List集合，一个存放所有日期；一个存放日期锁对应的数量
        List<Integer> num = videoVisitorCountVos.stream().map(VideoVisitorCountVo::getUserCount).collect(Collectors.toList());
        List<String> date = videoVisitorCountVos.stream().map(VideoVisitorCountVo::getJoinTime).collect(Collectors.toList());

        Map<String,Object> map = new HashMap<>();

        //x轴，y轴
        map.put("y",num);
        map.put("x",date);
        return message.add(map,200);
    }
}
