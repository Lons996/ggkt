package top.zwf666.ggkt.model.vod;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.zwf666.ggkt.model.base.BaseEntity;

import java.util.Date;

@Data
@ApiModel(description = "Teacher")
public class Teacher extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String intro;

    private String career;

    private Integer level;

    private String avatar;

    private Integer sort;

    @ApiModelProperty(value = "入驻时间")
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date joinDate;

}