package core.common.domain;

import core.base.domain.BaseDataModel;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/2.
 */
public class GridParam extends BaseDataModel{

    private Integer offset;
    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
