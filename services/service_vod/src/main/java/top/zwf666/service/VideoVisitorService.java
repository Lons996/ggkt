package top.zwf666.service;

import org.springframework.stereotype.Service;
import top.zwf666.mycreatebean.util.Message;

public interface VideoVisitorService {
    Message getData(String endDate, String startDate, Long courseId);

}
