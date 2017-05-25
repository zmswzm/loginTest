package core.base.resultmodel;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/23.
 */
public abstract class ResultModel extends AbstractView {

    protected Logger logger = Logger.getLogger(getClass());

    protected enum ContentType{
        TEXT("text/plain"),
        JAVASCRIPT("text/javascript"),
        JSON("text/json"),
        XML("text/xml"),
        HTML("text/html"),
        ZIP("application/zip"),
        STREAM("application/octet-stream");

        private String value;

        ContentType(String value){
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setContentType(getResultContentType().toString());
        render(response);
    }

    protected abstract void render(HttpServletResponse response) throws Exception;

    protected abstract ContentType getResultContentType();
}
