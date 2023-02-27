package top.zwf666.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zwf666.ggkt.vo.order.OrderInfoQueryVo;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.service.OrderDetailService;
import top.zwf666.service.OrderInfoService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderDetailService orderDetailService;

    //条件分页查询
    @PostMapping("selectToVo/{page}/{limit}")
    public Message selectToVo(@PathVariable Integer page, @PathVariable Integer limit,
                              @RequestBody(required = false) OrderInfoQueryVo orderInfoQueryVo) {
        return orderInfoService.selectToVo(page, limit, orderInfoQueryVo);
    }
}
