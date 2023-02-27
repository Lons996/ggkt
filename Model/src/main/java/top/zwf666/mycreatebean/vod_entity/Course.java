package top.zwf666.mycreatebean.vod_entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(value = {"handler"})
public class Course {
    private Long id;

    private Long teacherId;
    private String teacher; //接收教师信息

    private Long subjectId;
    private String subject;//接收课程子级信息

    private Long subjectParentId;
    private String subjectParent;//接收课程父级信息

    private String title;

    private Double price;

    private Integer lessonNum;

    private Integer durationSum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private Byte status;

    private Date publishTime;

    private Date createTime;

    private Date updateTime;

    private Byte isDeleted;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", teacher=" + teacher +
                ", subjectId=" + subjectId +
                ", subject=" + subject +
                ", subjectParentId=" + subjectParentId +
                ", subjectParent=" + subjectParent +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", lessonNum=" + lessonNum +
                ", durationSum=" + durationSum +
                ", cover='" + cover + '\'' +
                ", buyCount=" + buyCount +
                ", viewCount=" + viewCount +
                ", status=" + status +
                ", publishTime=" + publishTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectParent() {
        return subjectParent;
    }

    public void setSubjectParent(String subjectParent) {
        this.subjectParent = subjectParent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSubjectParentId() {
        return subjectParentId;
    }

    public void setSubjectParentId(Long subjectParentId) {
        this.subjectParentId = subjectParentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public Integer getDurationSum() {
        return durationSum;
    }

    public void setDurationSum(Integer durationSum) {
        this.durationSum = durationSum;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}