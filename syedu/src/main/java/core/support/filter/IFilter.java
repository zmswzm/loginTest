package core.support.filter;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;

/**
 * Created by Administrator on 2017/7/12.
 */
public interface IFilter {

    Logger logger = Logger.getLogger(IFilter.class);

    public void prepare(ServletContext context) throws Exception;
}