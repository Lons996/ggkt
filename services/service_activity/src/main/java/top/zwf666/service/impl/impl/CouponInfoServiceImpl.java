package top.zwf666.service.impl.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.zwf666.mapper.CouponInfoMapper;
import top.zwf666.mycreatebean.activity_entity.CouponInfo;
import top.zwf666.mycreatebean.util.Message;
import top.zwf666.service.CouponInfoService;

import java.util.List;

@Service
public class CouponInfoServiceImpl implements CouponInfoService {

    @Autowired
    private CouponInfoMapper couponInfoMapper;


    @Override
    public Message selectPageInfo(Long limit, Long page) {
        Message m = new Message();

        PageHelper.startPage(page.intValue(), limit.intValue());
        List<CouponInfo> couponInfoList = couponInfoMapper.selectAll();

        PageInfo<CouponInfo> pageInfo = new PageInfo<>(couponInfoList, 5);

        m.setMsg("查询成功");
        return m.add("pageInfo", pageInfo, 200);
    }

    @Override
    public Message getById(Long id) {
        Message m = new Message();
        CouponInfo couponInfo = couponInfoMapper.selectByPrimaryKey(id);
        m.add("entity", couponInfo, 200).setMsg("查询成功");
        return m;
    }

    @Override
    public Message save(CouponInfo couponInfo) {
        Message m = new Message();
        if (StringUtils.isEmpty(couponInfo.getCouponName())) {
            m.add("", "", 500).setMsg("优惠卷名称为空");
        } else if (StringUtils.isEmpty(couponInfo.getPublishCount())) {
            m.add("", "", 500).setMsg("发行数量为空");
        } else if (StringUtils.isEmpty(couponInfo.getStartTime())) {
            m.add("", "", 500).setMsg("未设置开始领取时间");
        } else if (StringUtils.isEmpty(couponInfo.getEndTime())) {
            m.add("", "", 500).setMsg("未设置结束领取时间");
        } else if (StringUtils.isEmpty(couponInfo.getExpireTime())) {
            m.add("", "", 500).setMsg("未设置过期时间");
        } else if (StringUtils.isEmpty(couponInfo.getRuleDesc())) {
            m.add("", "", 500).setMsg("规则为空");
        } else {
            int insert = couponInfoMapper.insert(couponInfo);
            m.setMsg("添加成功");
            m.add("newCouponId", couponInfo.getId(), 200);
        }
        return m;
    }

    @Override
    public Message updateById(CouponInfo couponInfo) {
        Message m = new Message();
        int i = couponInfoMapper.updateByPrimaryKey(couponInfo);
        m.add("", "", 200).setMsg("修改成功");
        return m;
    }

    @Override
    public Message removeById(Long id) {
        Message m = new Message();
        int i = couponInfoMapper.deleteByPrimaryKey(id);
        m.add("", "", 200).setMsg("删除成功");
        return m;
    }

    @Override
    public Message removeByIds(List<String> idList) {
        Message m = new Message();

        return m;
    }
}
