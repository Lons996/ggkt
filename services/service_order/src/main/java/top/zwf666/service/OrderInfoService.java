package top.zwf666.service;

import top.zwf666.ggkt.vo.order.OrderInfoQueryVo;
import top.zwf666.mycreatebean.util.Message;

public interface OrderInfoService  {
    Message selectToVo(Integer page, Integer limit, OrderInfoQueryVo orderInfoQueryVo);

}
