package core.extension.property_editor;

import core.common.CommonUtil.CommonUtils;

import java.beans.PropertyEditorSupport;

/**
 * Created by Administrator on 2017/6/16.
 */
public class TimestampEditor  extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {

        if(CommonUtils.TextUtil.isNotEmpty(text)){

            setValue(new java.sql.Timestamp(CommonUtils.DateUtil.parseDate(text).getTime()));
        }
    }

    @Override
    public String getAsText() {

        Object value = getValue();

        if(value != null && value instanceof java.sql.Timestamp){

            return CommonUtils.DateUtil.formatData((java.sql.Timestamp)value, CommonUtils.DateUtil.DATE_PATTERN.yyyy_MM_dd_HH_mm_ss);
        }

        return "";
    }
}

