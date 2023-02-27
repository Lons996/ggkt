package top.zwf666.service.impl.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zwf666.UserInfoClient;
import top.zwf666.ggkt.vo.activity.CouponUseQueryVo;
import top.zwf666.mapper.CouponUseMapper;
import top.zwf666.mycreatebean.activity_entity.CouponUse;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.service.CouponUseService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponUseServiceImpl implements CouponUseService {
    @Autowired
    private CouponUseMapper couponUseMapper;

    @Autowired
    private UserInfoClient userInfoClient;

    @Override
    public Message selectCouponUsePage(Long limit, Long page, CouponUseQueryVo couponUseQueryVo) {
        Message m = new Message();

        //分页：在查询之前传入页码及每页数
        PageHelper.startPage(page.intValue(), limit.intValue());
        List<CouponUse> couponUseList = couponUseMapper.selectPage(couponUseQueryVo);

        //保存用户信息
        List<Object> userInfoList = new ArrayList<>();

        if (!couponUseList.isEmpty()) {
            //构建userId遍历查询对应的用户数据
            couponUseList.forEach(item -> {
                Message user = userInfoClient.getUser(item.getUserId());
                userInfoList.add(user.getData().get("bean"));
            });

            PageInfo<CouponUse> pageInfo = new PageInfo<>(couponUseList, 5);

            m.add("pageInfo", pageInfo).add("UserInfo", userInfoList, 200);
        } else {
            m.add("", "", 200).setMsg("查询无数据");
        }
        return m;
    }
}
