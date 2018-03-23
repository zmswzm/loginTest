package core.base.resultmodel.internal;

import core.base.resultmodel.ResultModel;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/5/23.
 */
public abstract class TextBasedResultModel extends ResultModel {

    @Override
    protected void render(HttpServletResponse response) throws Exception {

        String text = getText();
        if(null != text){
            response.getWriter().write(text);
        }
    }

    protected abstract String getText();
    @Override
    protected ContentType getResultContentType() {
        return ContentType.TEXT;
    }
}
