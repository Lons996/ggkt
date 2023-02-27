package top.zwf666.service;

import top.zwf666.ggkt.vo.activity.CouponUseQueryVo;
import top.zwf666.mycreatebean.util.Message;

public interface CouponUseService {
    Message selectCouponUsePage(Long limit, Long page, CouponUseQueryVo couponUseQueryVo);
}