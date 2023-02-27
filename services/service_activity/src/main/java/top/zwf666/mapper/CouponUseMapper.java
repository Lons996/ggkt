package top.zwf666.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.zwf666.ggkt.vo.activity.CouponUseQueryVo;
import top.zwf666.mycreatebean.activity_entity.CouponUse;

import java.util.List;

@Mapper
public interface CouponUseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CouponUse record);

    CouponUse selectByPrimaryKey(Long id);

    List<CouponUse> selectAll();

    int updateByPrimaryKey(CouponUse record);

    List<top.zwf666.mycreatebean.activity_entity.CouponUse> selectPage(CouponUseQueryVo couponUseQueryVo);

}