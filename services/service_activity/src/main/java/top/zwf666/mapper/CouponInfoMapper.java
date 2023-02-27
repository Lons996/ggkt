package top.zwf666.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScans;
import top.zwf666.mycreatebean.activity_entity.CouponInfo;

@Mapper
public interface CouponInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CouponInfo record);

    CouponInfo selectByPrimaryKey(Long id);

    List<CouponInfo> selectAll();

    int updateByPrimaryKey(CouponInfo record);
}