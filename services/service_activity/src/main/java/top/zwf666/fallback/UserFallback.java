package top.zwf666.fallback;

import org.springframework.stereotype.Component;
import top.zwf666.ggkt.vo.activity.CouponUseQueryVo;
import top.zwf666.mycreatebean.util.Message;

@Component
public class UserFallback {
    public static Message myGetUserFallBack(CouponUseQueryVo couponUseQueryVo, Long limit, Long page, Throwable throwable) {
        Message m = new Message();
        m.add("", "", 555).setMsg("服务异常,请稍后再试！");
        return m;
    }
}
