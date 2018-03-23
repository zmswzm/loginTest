package core.common.utils;

import com.google.gson.Gson;
import core.common.CommonUtil.CommonUtils;

/**
 * GsonHttpMessageConverter 的 gson 工厂类
 */
public class GsonFactoryBeanForMessageConverter {

    /**
     * 生产 gson 对象
     *
     * @return
     */
    public Gson getGson(){

        //注意，需要与其它地方使用一个 gson 示例，以便保证相关的配置
        return CommonUtils.JsonUtil.getGson();
    }
}