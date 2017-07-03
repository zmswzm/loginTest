package core.support.message;

import core.base.resultmodel.internal.JsonBasedResultModel;
import core.common.CommonUtil.CommonUtils;
import core.common.exception.beans.impl.SystemExceptionDesc;
import core.common.exception.impl.BusinessException;
import core.common.exception.impl.SystemException;
import core.common.exception.impl.internal.framework.FrameworkInternalException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
public class JSONMessager extends JsonBasedResultModel {

    private ThreadLocal<Map<String, Object>> messages = new ThreadLocal<Map<String,Object>>();

    public JSONMessager put(String name, Object value){

        if(messages.get() == null){

            messages.set(new HashMap<String, Object>());
        }

        messages.get().put(name, value);

        return this;
    }

    public JSONMessager success(){

        return put("success", true);
    }

    public JSONMessager failure(){

        return put("success", false);
    }
    protected Object getObject() {

        if(messages.get() == null){

            throw new BusinessException(new SystemExceptionDesc("消息内容为空"));
        }

        Map<String, Object> data = messages.get();

        //擦除变量
        messages.remove();

        return data;
    }

    public void send(HttpServletResponse response){

        if(messages.get() == null){

            throw new BusinessException(new SystemExceptionDesc("消息内容为空"));
        }

        Map<String, Object> data = messages.get();

        //擦除变量
        messages.remove();

        try {
            response.setContentType(ContentType.JSON.toString());
            response.getWriter().write(CommonUtils.JsonUtil.object2Json(data));
        } catch (Exception e) {
            throw new FrameworkInternalException(new SystemExceptionDesc(e));
        }
    }
}
