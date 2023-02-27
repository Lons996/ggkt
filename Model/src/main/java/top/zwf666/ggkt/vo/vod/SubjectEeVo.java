package top.zwf666.ggkt.vo.vod;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * <p>
 * Dict
 * </p>
 *
 * @author qy
 */
@Data
public class SubjectEeVo {

	@ExcelProperty(value = "id" )
	private Long id;

	@ExcelProperty(value = "课程分类名称")
	private String title;

	@ExcelProperty(value = "上级id")
	private Long parentId;

	@ExcelProperty(value = "排序")
	private Integer sort;


}

