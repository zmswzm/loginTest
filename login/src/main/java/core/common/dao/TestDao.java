package core.common.dao;

import core.base.dao.JdbcBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/19.
 */
@Repository("testDao")
public class TestDao extends JdbcBaseDao implements ITestDao{
    public void addTest(String a) {
        String sql = "INSERT INTO TEXT(c,a,b) VALUES(:c,:a,:b)";
        Map<String,Object> p = new HashMap<String,Object>();
        p.put("c","辅导费");
        p.put("a","aaaa");
        p.put("b",12);
        this.namedParameterJdbcTemplate.update(sql,p);
    }
}
