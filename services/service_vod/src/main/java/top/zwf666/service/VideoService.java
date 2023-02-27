package top.zwf666.service;


import top.zwf666.mycreatebean.util.Message;

import java.util.Map;

public interface VideoService {

    int removeVideoByCourseId(Long id);

    void removeVideoFile(Long id);

    Message selectAll(Map<String,Integer[]> arr);

}
