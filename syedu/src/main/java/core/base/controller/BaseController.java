package core.base.controller;

import core.extension.property_editor.SqlDateEditor;
import core.extension.property_editor.TimestampEditor;
import core.extension.property_editor.UtilDateEditor;
import core.support.message.Messager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;

/**
 * Created by Administrator on 2017/6/16.
 */
public class BaseController {
    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    @Qualifier("messager")
    protected Messager messager;

    public BaseController(){}

    protected void initBinder(WebDataBinder binder){

        binder.registerCustomEditor(java.util.Date.class,new UtilDateEditor());
        binder.registerCustomEditor(java.sql.Date.class,new SqlDateEditor());
        binder.registerCustomEditor(java.sql.Timestamp.class,new TimestampEditor());
    }
}
