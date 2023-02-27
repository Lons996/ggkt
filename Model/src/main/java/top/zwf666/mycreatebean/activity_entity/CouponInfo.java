package top.zwf666.mycreatebean.activity_entity;

import java.math.BigDecimal;

public class CouponInfo {
    private Long id;

    private Byte couponType;

    private String couponName;

    private BigDecimal amount;

    private BigDecimal conditionAmount;

    private String startTime;
    private String endTime;

    private Byte rangeType;

    private String ruleDesc;

    private Integer publishCount;

    private Integer perLimit;

    private Integer useCount;

    private Integer receiveCount;

    private String expireTime;

    private Boolean publishStatus;

    private String createTime;

    private String updateTime;

    private Byte isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getCouponType() {
        return couponType;
    }

    public void setCouponType(Byte couponType) {
        this.couponType = couponType;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getConditionAmount() {
        return conditionAmount;
    }

    public void setConditionAmount(BigDecimal conditionAmount) {
        this.conditionAmount = conditionAmount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Byte getRangeType() {
        return rangeType;
    }

    public void setRangeType(Byte rangeType) {
        this.rangeType = rangeType;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
    }

    public Integer getPublishCount() {
        return publishCount;
    }

    public void setPublishCount(Integer publishCount) {
        this.publishCount = publishCount;
    }

    public Integer getPerLimit() {
        return perLimit;
    }

    public void setPerLimit(Integer perLimit) {
        this.perLimit = perLimit;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public Integer getReceiveCount() {
        return receiveCount;
    }

    public void setReceiveCount(Integer receiveCount) {
        this.receiveCount = receiveCount;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Boolean publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "CouponInfo{" +
                "id=" + id +
                ", couponType=" + couponType +
                ", couponName='" + couponName + '\'' +
                ", amount=" + amount +
                ", conditionAmount=" + conditionAmount +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", rangeType=" + rangeType +
                ", ruleDesc='" + ruleDesc + '\'' +
                ", publishCount=" + publishCount +
                ", perLimit=" + perLimit +
                ", useCount=" + useCount +
                ", receiveCount=" + receiveCount +
                ", expireTime='" + expireTime + '\'' +
                ", publishStatus=" + publishStatus +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}