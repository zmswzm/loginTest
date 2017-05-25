package core.base.resultmodel.internal;

import core.base.resultmodel.ResultModel;
import core.common.CommonUtil.CommonUtils;

/**
 * Created by Administrator on 2017/5/23.
 */
public abstract class JsonBasedResultModel extends TextBasedResultModel {

    @Override
    protected String getText() {

        Object obj = getObject();
        if (null != obj) {
            if (obj instanceof String) {
                return obj + "";
            }else {
            return CommonUtils.JsonUtil.object2Json(obj);
        }
    }
        return null;
    }

    @Override
    protected ContentType getResultContentType() {
        return super.getResultContentType();
    }

    protected abstract Object getObject();
}
