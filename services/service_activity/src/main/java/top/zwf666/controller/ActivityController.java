package top.zwf666.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zwf666.fallback.UserFallback;
import top.zwf666.ggkt.vo.activity.CouponUseQueryVo;
import top.zwf666.mycreatebean.activity_entity.CouponInfo;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.service.CouponInfoService;
import top.zwf666.service.CouponUseService;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private CouponUseService couponUseService;
    @Autowired
    private CouponInfoService couponInfoService;

    //获取已经使用的优惠券分页列表
    @PostMapping("couponUse/{page}/{limit}")
    @SentinelResource(value = "couponUse", fallbackClass = UserFallback.class, fallback = "myGetUserFallBack")
    public Message index(@RequestBody(required = false) CouponUseQueryVo couponUseQueryVo, @PathVariable Long limit, @PathVariable Long page) {
        return couponUseService.selectCouponUsePage(limit, page, couponUseQueryVo);
    }

    //获取优惠券分页列表
    @GetMapping("couponInfo/{page}/{limit}")
    public Message index(@PathVariable Long limit, @PathVariable Long page) {
        return couponInfoService.selectPageInfo(limit, page);
    }

    //获取优惠券
    @GetMapping("get/{id}")
    public Message get(@PathVariable Long id) {
        return couponInfoService.getById(id);
    }

    //新增优惠券")
    @PostMapping("save")
    public Message save(@RequestBody CouponInfo couponInfo) {
        return couponInfoService.save(couponInfo);
    }

    //修改优惠券")
    @PostMapping("update")
    public Message updateById(@RequestBody CouponInfo couponInfo) {
        return couponInfoService.updateById(couponInfo);
    }

    //删除优惠券")
    @GetMapping("remove/{id}")
    public Message remove(@PathVariable Long id) {
        return couponInfoService.removeById(id);
    }

    //根据id列表删除优惠券")
    @DeleteMapping("batchRemove")
    public Message batchRemove(@RequestBody List<String> idList) {
        return couponInfoService.removeByIds(idList);
    }
}

















