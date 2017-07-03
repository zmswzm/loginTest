package core.extension.property_editor;

import core.common.CommonUtil.CommonUtils;

import java.beans.PropertyEditorSupport;

/**
 * Created by Administrator on 2017/6/16.
 */
public class SqlDateEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Object value = getValue();

        if(value != null && value instanceof java.sql.Date){

            return CommonUtils.DateUtil.formatData((java.sql.Date)value, CommonUtils.DateUtil.DATE_PATTERN.yyyy_MM_dd_HH_mm_ss);
        }
        return "";
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if(CommonUtils.TextUtil.isNotEmpty(text)){

            setValue(new java.sql.Date(CommonUtils.DateUtil.parseDate(text).getTime()));
        }
    }
}
