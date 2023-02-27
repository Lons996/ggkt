package top.zwf666.service;

import top.zwf666.ggkt.vo.activity.CouponUseQueryVo;
import top.zwf666.mycreatebean.activity_entity.CouponInfo;
import top.zwf666.mycreatebean.util.Message;

import java.util.List;

public interface CouponInfoService {
    Message selectPageInfo(Long limit, Long page);

    Message getById(Long id);

    Message save(CouponInfo couponInfo);

    Message updateById(CouponInfo couponInfo);

    Message removeById(Long id);

    Message removeByIds(List<String> idList);
}
