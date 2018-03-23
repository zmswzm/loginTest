package core.manage.course.vo;

import core.base.domain.BaseDataModel;

/**
 * Created by Administrator on 2017/7/2.
 */
public class CourseInfoVo extends BaseDataModel{

    private String cid;
    private String title;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
