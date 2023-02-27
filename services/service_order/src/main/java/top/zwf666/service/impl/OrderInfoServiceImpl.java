package top.zwf666.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zwf666.ggkt.model.order.OrderDetail;
import top.zwf666.ggkt.vo.order.OrderInfoQueryVo;
import top.zwf666.mapper.OrderDetailMapper;
import top.zwf666.mapper.OrderInfoMapper;
import top.zwf666.mycreatebean.order_entity.OrderInfo;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.service.OrderInfoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Message selectToVo(Integer page, Integer limit, OrderInfoQueryVo orderInfoQueryVo) {
        Message m = new Message();

        //分页：在查询之前传入页码及每页数
        PageHelper.startPage(page, limit);
        List<OrderInfo> orderInfoQueryVoList = orderInfoMapper.selectToVoPage(orderInfoQueryVo);
        //使用pageInfo 包装查询后的结果 (封装了详细的分页信息,连续显示的页数(页码))
        PageInfo<OrderInfo> empPageInfo = new PageInfo<>(orderInfoQueryVoList, 5);

        //保存订单详情信息
        List<OrderDetail> orderDetailList = new ArrayList<>();
        List<OrderInfo> list = empPageInfo.getList();
        if (list.isEmpty()) {
            m.add("", "", 200).setMsg("无数据");
        } else {
            list.forEach(item -> {
                orderDetailList.add(orderDetailMapper.selectByOrderId(item.getId()));
            });
            m.add("OrderInfo", empPageInfo).add("OrderDetail",orderDetailList,200).setMsg("查询成功！");
        }
        return m;
    }
}
