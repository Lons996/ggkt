package top.zwf666.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zwf666.mycreatebean.order_entity.OrderDetail;

@Mapper
public interface OrderDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long id);

    List<OrderDetail> selectAll();

    int updateByPrimaryKey(OrderDetail record);

    top.zwf666.ggkt.model.order.OrderDetail selectByOrderId(@Param("orderId") Long id);

}