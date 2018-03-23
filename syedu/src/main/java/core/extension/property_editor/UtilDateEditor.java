package core.extension.property_editor;

import core.common.CommonUtil.CommonUtils;

import java.beans.PropertyEditorSupport;

/**
 * Created by Administrator on 2017/6/16.
 */
public class UtilDateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {

        if(CommonUtils.TextUtil.isNotEmpty(text)){

            setValue(CommonUtils.DateUtil.parseDate(text));
        }
    }

    @Override
    public String getAsText() {

        Object value = getValue();

        if(value != null && value instanceof java.util.Date){

            return CommonUtils.DateUtil.formatData((java.util.Date)value, CommonUtils.DateUtil.DATE_PATTERN.yyyy_MM_dd_HH_mm_ss);
        }

        return "";
    }
}