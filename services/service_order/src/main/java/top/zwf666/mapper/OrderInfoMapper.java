package top.zwf666.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import top.zwf666.ggkt.vo.order.OrderInfoQueryVo;
import top.zwf666.mycreatebean.order_entity.OrderInfo;

@Mapper
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    List<OrderInfo> selectAll();

    int updateByPrimaryKey(OrderInfo record);

    List<OrderInfo> selectToVoPage(OrderInfoQueryVo orderInfoQueryVo);

}