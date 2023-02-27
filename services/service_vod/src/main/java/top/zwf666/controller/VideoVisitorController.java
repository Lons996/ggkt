package top.zwf666.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.service.VideoVisitorService;

@RestController
//@CrossOrigin
@RequestMapping("/vod/videoVisitor")
public class VideoVisitorController {

    @Autowired
    private VideoVisitorService videoVisitorService;

    @RequestMapping("/getVideoVisitor/{courseId}/{startDate}/{endDate}")
    public Message getVideoVisitor( @PathVariable Long courseId,@PathVariable String startDate, @PathVariable String endDate){
        return videoVisitorService.getData(endDate,startDate,courseId);
    }
}
