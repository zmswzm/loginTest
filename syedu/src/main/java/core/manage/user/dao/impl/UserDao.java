package core.manage.user.dao.impl;

import core.base.dao.JdbcBaseDao;
import core.common.CommonUtil.CommonUtils;
import core.common.domain.GridParam;
import core.manage.user.dao.IUserDao;
import core.manage.user.domain.AccessControlUser;
import core.manage.user.domain.CuserInfo;
import core.manage.user.domain.CuserPer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/2.
 */
@Repository("UserDao")
public class UserDao extends JdbcBaseDao implements IUserDao{

    public AccessControlUser getUserByPhone(String phone) {
        String sql = "SELECT * FROM access_control_user acu WHERE acu.PHONE = :phone ";
        Map param = new HashMap<String,Object>();
        param.put("phone",phone);
        List<AccessControlUser> userList  = this.namedParameterJdbcTemplate.query(sql,param,new UserMapper());
        return userList!=null&&userList.size() > 0 ? userList.get(0) : null;
    }

    public List<Map<String,Object>> getUserByType() {
        String sql = "SELECT acu.uid FROM access_control_user acu WHERE acu.user_type <> '3' AND  acu.user_type <> '1'";
        Map param = new HashMap<String,Object>();
        return this.namedParameterJdbcTemplate.queryForList(sql,param);
    }

    public List<Map<String,Object>> getUserByUid(String uid) {
        String sql = "SELECT acu.uid FROM access_control_user acu WHERE acu.REFEREE = :uid ";
        Map param = new HashMap<String,Object>();
        param.put("uid",uid);
        return this.namedParameterJdbcTemplate.queryForList(sql,param);
    }

    public List<Map<String,Object>> getUserPer(GridParam gp, CuserPer cuserPer){
        String sql = "SELECT " +
                "  acu.UID AS uid," +
                "  acu.NAME AS uname," +
                "  acu.PHONE AS uphone," +
                "  ci.ID AS cid," +
                "  ci.TITLE AS cname," +
                "  s.schoolName, " +
                "  per.per " +
                "FROM" +
                "  access_control_user acu " +
                "  JOIN percentage per " +
                "    ON per.uid = acu.UID " +
                "  JOIN course_info ci " +
                "    ON ci.ID = per.cid " +
                "  LEFT JOIN school s " +
                "    ON ci.SCHOOLID = s.id  " +
                "WHERE 1=1 ";
        Map<String,Object> p = new HashMap<>();
        if(CommonUtils.TextUtil.isNotEmpty(cuserPer.getUid())){
            sql = sql + " AND acu.UID = :UID ";
            p.put("UID",cuserPer.getUid());
        }
        if(CommonUtils.TextUtil.isNotEmpty(cuserPer.getCid())){
            sql = sql + " AND ci.ID = :cid ";
            p.put("cid",cuserPer.getCid());
        }
        if(null != gp.getOffset() && null != gp.getLimit()){
            sql = sql + " LIMIT :offset,:limit";
            p.put("offset",gp.getOffset());
            p.put("limit",gp.getLimit());
        }
        return this.namedParameterJdbcTemplate.queryForList(sql,p);
    }

    public Integer getPCount(CuserPer cuserPer){
        String sql = "SELECT " +
                " COUNT(per.uid) " +
                "FROM" +
                "  percentage per " +
                "  JOIN access_control_user acu " +
                "    ON per.uid = acu.UID " +
                "  JOIN course_info ci " +
                "    ON ci.ID = per.cid " +
                "  LEFT JOIN school s " +
                "    ON ci.SCHOOLID = s.id  " +
                "WHERE 1=1 ";
        Map<String,Object> p = new HashMap<>();
        if(CommonUtils.TextUtil.isNotEmpty(cuserPer.getUid())){
            sql = sql + " AND acu.UID = :UID ";
            p.put("UID",cuserPer.getUid());
        }
        if(CommonUtils.TextUtil.isNotEmpty(cuserPer.getCid())){
            sql = sql + " AND ci.ID = :cid ";
            p.put("cid",cuserPer.getCid());
        }
        return this.namedParameterJdbcTemplate.queryForObject(sql, p, Integer.class);
    }

    public List<AccessControlUser> getUser(GridParam gp, CuserInfo cui){
        String sql = "SELECT " +
                " acu.UID," +
                " acu.NAME," +
                " acu.PASSWORD," +
                " acu.PHONE," +
                " acu.ID_CARD_NO," +
                " acu.SEX," +
                " acu.BANK_CARD_NO," +
                " acu.BANK_NAME," +
                " acu.user_type," +
                " a.NAME AS REFEREE " +
                "FROM" +
                " access_control_user acu " +
                " LEFT JOIN access_control_user a " +
                " ON acu.REFEREE = a.UID " +
                "WHERE 1=1 ";
        Map<String,Object> p = new HashMap<>();
        if(CommonUtils.TextUtil.isNotEmpty(cui.getCname())){
            sql = sql + " AND acu.NAME = :cname ";
            p.put("cname",cui.getCname());
        }
        if(CommonUtils.TextUtil.isNotEmpty(cui.getCphone())){
            sql = sql + " AND acu.PHONE = :cPHONE ";
            p.put("cPHONE",cui.getCphone());
        }
        if(CommonUtils.TextUtil.isNotEmpty(cui.getCreferee())){
            sql = sql + " AND a.PHONE = :PHONE ";
            p.put("PHONE",cui.getCreferee());
        }
        if(CommonUtils.TextUtil.isNotEmpty(cui.getCmajor())){
            sql = sql + " AND acu.user_type = :userType ";
            p.put("userType",cui.getCmajor());
        }
        if(null != gp.getOffset() && null != gp.getLimit()){
            sql = sql + " LIMIT :offset,:limit";
            p.put("offset",gp.getOffset());
            p.put("limit",gp.getLimit());
        }
        List<AccessControlUser> stuList  = this.namedParameterJdbcTemplate.query(sql,p,new UserMapper());
        return stuList;
    }

    public Integer getCount(CuserInfo cui){
        String sql = "SELECT " +
                " COUNT(acu.UID) " +
                "FROM" +
                " access_control_user acu " +
                " LEFT JOIN access_control_user a " +
                " ON acu.REFEREE = a.UID " +
                "WHERE 1=1 ";
        Map<String,Object> p = new HashMap<>();
        if(CommonUtils.TextUtil.isNotEmpty(cui.getCname())){
            sql = sql + " AND acu.NAME = :cname ";
            p.put("cname",cui.getCname());
        }
        if(CommonUtils.TextUtil.isNotEmpty(cui.getCphone())){
            sql = sql + " AND acu.PHONE = :cPHONE ";
            p.put("cPHONE",cui.getCphone());
        }
        if(CommonUtils.TextUtil.isNotEmpty(cui.getCreferee())){
            sql = sql + " AND a.PHONE = :PHONE ";
            p.put("PHONE",cui.getCreferee());
        }
        if(CommonUtils.TextUtil.isNotEmpty(cui.getCmajor())){
            sql = sql + " AND acu.user_type = :userType ";
            p.put("userType",cui.getCmajor());
        }
        return this.namedParameterJdbcTemplate.queryForObject(sql, p, Integer.class);
    }

    @Override
    public AccessControlUser getUserByPhoneAndPassword(String phone, String password) {
        String sql = "SELECT * FROM access_control_user acu WHERE acu.PHONE = :phone AND acu.PASSWORD = :password";
        Map param = new HashMap<String,Object>();
        param.put("phone",phone);
        param.put("password",password);
        List<AccessControlUser> userList  = this.namedParameterJdbcTemplate.query(sql,param,new UserMapper());
        return userList!=null&&userList.size() > 0 ? userList.get(0) : null;
    }

    public List<AccessControlUser> getAllUser() {
        String sql = "SELECT * FROM access_control_user ";
        List<AccessControlUser> userList  = this.namedParameterJdbcTemplate.query(sql,new UserMapper());
        return userList;
    }

    public int deleteUser(String uid) {
        String sql = "DELETE FROM access_control_user WHERE UID = :uid ";
        Map param = new HashMap<String,Object>();
        param.put("uid",uid);
        return this.namedParameterJdbcTemplate.update(sql,param);
    }

    public int saveUser(AccessControlUser user) {
        String sql = "INSERT INTO access_control_user(uid,phone,password,name,sex,referee,user_type,ID_CARD_NO,BANK_CARD_NO,BANK_NAME) " +
                "VALUES(:uid,:phone,:password,:name,:sex,:referee,:userType,:idCardNo,:bankNo,:bankName)";
        Map param = new HashMap<String,Object>();
        param.put("uid",user.getUid());
        param.put("phone",user.getPhone());
        param.put("password",user.getPassword());
        param.put("name",user.getName());
        param.put("sex",user.getSex());
        param.put("referee",user.getReferee());
        param.put("userType",user.getUserType());
        param.put("idCardNo",user.getIdCardNo());
        param.put("bankNo",user.getBankCardNo());
        param.put("bankName",user.getBankName());
        return this.namedParameterJdbcTemplate.update(sql,param);
    }

    public int saveUserPercent(String uid,int cid) {
        String sql = "INSERT INTO percentage(uid,cid) VALUES(:uid,:cid)";
        Map param = new HashMap<String,Object>();
        param.put("uid",uid);
        param.put("cid",cid);
        return this.namedParameterJdbcTemplate.update(sql,param);
    }

    public int updateUserPercent(String uid,String cid,String per) {
        String sql = "UPDATE percentage SET per = :per WHERE uid = :uid AND cid = :cid";
        Map param = new HashMap<String,Object>();
        param.put("uid",uid);
        param.put("cid",cid);
        param.put("per",per);
        return this.namedParameterJdbcTemplate.update(sql,param);
    }

    public int updateUser(AccessControlUser user) {
        String sql = "UPDATE access_control_user SET user_type = :user_type WHERE UID = :UID";
        Map param = new HashMap<String,Object>();
        param.put("UID",user.getUid());
        param.put("user_type",user.getUserType());
        return this.namedParameterJdbcTemplate.update(sql,param);
    }

    public int updatePW(AccessControlUser user) {
        String sql = "UPDATE access_control_user SET PASSWORD = :password WHERE phone = :phone";
        Map param = new HashMap<String,Object>();
        param.put("phone",user.getPhone());
        param.put("password",user.getNewPassword());
        return this.namedParameterJdbcTemplate.update(sql,param);
    }

    class UserMapper implements RowMapper<AccessControlUser> {

        public AccessControlUser mapRow(ResultSet rs, int rowNum) throws SQLException {

            AccessControlUser userInfro = new AccessControlUser();
            userInfro.setUid(rs.getString("UID"));
            userInfro.setPhone(rs.getString("PHONE"));
            userInfro.setPassword(rs.getString("PASSWORD"));
            userInfro.setName(rs.getString("NAME"));
            userInfro.setSex(rs.getString("SEX"));
            userInfro.setUserType(rs.getString("user_type"));
            userInfro.setIdCardNo(rs.getString("ID_CARD_NO"));
            userInfro.setBankCardNo(rs.getString("BANK_CARD_NO"));
            userInfro.setBankName(rs.getString("BANK_NAME"));
            userInfro.setReferee(rs.getString("REFEREE"));
            return userInfro;
        }
    }
}
