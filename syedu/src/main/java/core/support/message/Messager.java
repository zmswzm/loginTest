package core.support.message;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/2.
 */
@Component("messager")
public class Messager {

    protected JSONMessager jsonMessager;


    public Messager(){

        jsonMessager = new JSONMessager();
    }

    public JSONMessager jsonMessager() {
        return jsonMessager;
    }

}
