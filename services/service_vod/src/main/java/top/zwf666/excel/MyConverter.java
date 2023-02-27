package top.zwf666.excel;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import top.zwf666.ggkt.vo.vod.SubjectVo;

import java.util.List;

public class MyConverter implements Converter<List> {

    /**
     * 转换成的Java类型
     */
    @Override
    public Class supportJavaTypeKey() {
        return List.class;
    }

    /**
     * Excel表中的类型
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 在读取时，将Excel的值转换为Java值的规则
     */
    @Override
    public List convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    /**
     * 在写入时，将Java的值转换为Excel值的规则
     */
    @Override
    public CellData convertToExcelData(List value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }
}
