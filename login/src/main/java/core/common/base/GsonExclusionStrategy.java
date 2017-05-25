package core.common.base;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by Administrator on 2017/5/23.
 */
public class GsonExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipField(FieldAttributes f) {
        return false;
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        if (clazz == Throwable.class){
            return true;
        }
        return false;
    }
}
